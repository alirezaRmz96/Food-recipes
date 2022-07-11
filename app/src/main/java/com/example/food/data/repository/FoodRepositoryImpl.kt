package com.example.food.data.repository

import android.util.Log
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.repository.dataSource.FoodLocalDataSource
import com.example.food.data.repository.dataSource.FoodRemoteDataSource
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository
import kotlinx.coroutines.flow.*
import retrofit2.Response

class FoodRepositoryImpl(
    private val foodRemoteDataSource: FoodRemoteDataSource,
    private val foodLocalDataSource: FoodLocalDataSource
) :FoodRepository{


//    override  fun getFoodDish(ingredients: String): Flow<List<SpecialFood>> {
//        return  getFoodFromDB(ingredients)
//    }
//
//    private fun getFoodFromDB(ingredients: String):Flow<List<SpecialFood>>{
//        lateinit var food : Flow<List<SpecialFood>>
//        try {
//            food = foodLocalDataSource.getFoodDish()
//        }catch (exception: Exception) {
//            Log.i("MyTag", exception.message.toString())
//        }
//        if (food){
//            return food
//        }else{
//            food = getFoodFromApi(ingredients)
//            foodLocalDataSource.saveFoodDishData(food)
//        }
//        return  food
//    }
//    private suspend fun getFoodFromApi(ingredients: String):Flow<List<SpecialFood>>{
//        lateinit var food : Flow<List<SpecialFood>>
//        try {
//            val response = foodRemoteDataSource.getInformationFood(ingredients)
//            if(response.isSuccessful){
//                response.body()?.let {result->
//                     food = Resource.Success(result)
//                }
//            }
//
//
//        }
//    }

    // ------------- from api


    override suspend fun getInformation(ingredients: String): Resource<SpecialFood> {
        return responseToResource(
            foodRemoteDataSource.getInformationFood(ingredients)
        )
    }

    override suspend fun getRecepFromID(id: Int): Resource<RecepFromIdList> {
        return responseToResource(
            foodRemoteDataSource.getRecepFromId(id)
        )
    }

    private fun <T> responseToResource(response : Response<T>):Resource<T>{
        if(response.isSuccessful){
            response.body()?.let {result->
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

    override fun getFoodDish(ingredients: String): Flow<List<SpecialFood>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFavDishDetails(recepFromIdList: RecepFromIdList) {
        foodLocalDataSource.updateFavDishDetails(recepFromIdList)
    }
}