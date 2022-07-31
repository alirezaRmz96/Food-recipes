package com.example.food.data.repository.workManager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.data.repository.FoodRepositoryImpl
import com.example.food.data.repository.dataSource.FoodRemoteDataSource
import com.example.food.data.repository.dataSourceImpl.FoodRemoteDataSourceImpl
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class OneTimeRequestWorker(
    private val foodRemoteDataSource: FoodRemoteDataSourceImpl,
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    private val latestFoodMutex = Mutex()
    private var lastString: String? = null
    private var latestFoods: List<AllFoodResultList> = emptyList()

    override suspend fun doWork(): Result = try {
        Log.d("TAG", "doWork: ")
        val inputValue = inputData.getString("inputKey")

        if (lastString?.length != inputValue?.length || latestFoods.isEmpty()) {

            val networkResult = foodRemoteDataSource.getInformationFood(lastString!!)
            latestFoodMutex.withLock {
                if (networkResult.isSuccessful) {
                    networkResult.body()?.let { result ->
                        this.latestFoods = result
                    }
                }
            }
            lastString = inputValue
            Log.i("MyTag", "Latest lastString: $lastString")
        }
        Result.success(createOutputData(
            latestFoodMutex.withLock {
                this.latestFoods
            }
        ))
    } catch (error: Throwable) {
        Result.failure(createFailureData())
    }
}

//// Method to create output data
private fun createOutputData(list: List<AllFoodResultList>): Data {
    return Data.Builder()
        .put("FETCH_LATEST_FOODS_TASK", list)
        .build()
}

private fun createFailureData(): Data {

    return Data.Builder()
        .putString("FETCH_FAILURE_TASK", "Some thing went wrong")
        .build()
}
//
//object Companion {
//    fun logger(message: String) =
//        Log.i("WorkRequest Status", message)
//}
