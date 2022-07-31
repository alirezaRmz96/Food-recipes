package com.example.food.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.repository.dataSource.FoodLocalDataSource
import com.example.food.data.repository.dataSource.FoodRemoteDataSource
import com.example.food.data.repository.workManager.RefreshLatestFoodWorker
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.Response

class FoodRepositoryImpl(
    private val foodRemoteDataSource: FoodRemoteDataSource,
    private val foodLocalDataSource: FoodLocalDataSource,

) : FoodRepository {



    //To protect reads and writes from different threads, a Mutex is used.
    private val latestFoodMutex = Mutex()
    private var lastString: String? = null

    // Cache of the latest foods got from the network.
    private var latestFoods: List<AllFoodResultList> = emptyList()


    // first because i can't use flow for list in getFromApi so i use only list until see what happened
//    Flow<List<AllFoodResultList>>
    override suspend fun getFood(ingredients: String): List<AllFoodResultList> {

        return getLatestFood(ingredients)
    }

    private suspend fun getLatestFood(ingredients: String): List<AllFoodResultList> {
        Log.d("TAG", "Latest ingredients: $ingredients")

        /*
        we can use coroutineScope to make an operation live longer than the screen
        that's because if user navigates away from the screen and network in progress
        it'll be canceled and the result wont be cached then we must to use coroutineScope
        and i actually don't need it
        * */
        /**
        return if(lastString?.length != ingredients.length){
        externalScope.async {
        foodRemoteDataSource.getInformationFood1(ingredients).body()!!.also { networkResult->
        latestFoodMutex.withLock {
        latestFoods = networkResult
        }
        }
        }.await()
        }else{ return latestFoodMutex.withLock{ this.latestFoods }
        }
         */

        if (lastString?.length != ingredients.length || latestFoods.isEmpty()) {
            val networkResult = foodRemoteDataSource.getInformationFood(ingredients)
            latestFoodMutex.withLock {
                if (networkResult.isSuccessful) {
                    networkResult.body()?.let { result ->
                        this.latestFoods = result
                    }
                }

            }
            lastString = ingredients
            Log.d("TAG", "Latest lastString: $lastString")
        }
        return latestFoodMutex.withLock {
            this.latestFoods
        }
    }

    // ------------- from api


    override suspend fun getRecepFromID(id: Int): Resource<RecepFromIdList> {
        return responseToResource(
            foodRemoteDataSource.getRecepFromId(id)
        )
    }

    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    // ---------- from local
    override suspend fun insertFoodData(recepFromIdList: RecepFromIdList) {
        foodLocalDataSource.insertFoodDishData(recepFromIdList)
    }

    override fun getFavoriteDish(): Flow<List<RecepFromIdList>> {
        return foodLocalDataSource.getFavoriteDish()
    }

    override suspend fun deleteFavDishDetails(recepFromIdList: RecepFromIdList) {
        foodLocalDataSource.deleteFavDishDetails(recepFromIdList)
    }


    override suspend fun updateFavDishDetails(recepFromIdList: RecepFromIdList) {
        foodLocalDataSource.updateFavDishDetails(recepFromIdList)
    }
}