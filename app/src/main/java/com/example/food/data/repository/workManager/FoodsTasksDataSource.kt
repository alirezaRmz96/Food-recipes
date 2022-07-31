package com.example.food.data.repository.workManager

import androidx.work.*
import java.util.concurrent.TimeUnit

//private const val REFRESH_RATE_HOURS = 4L
//private const val FETCH_LATEST_FOODS_TASK = "FetchLatestFoodsTask"
//private const val TAG_FETCH_LATEST_FOODS = "FetchLatestFoodsTaskTag"

class FoodsTasksDataSource (
    private val workManager: WorkManager
    ){
    companion object{
        const val REFRESH_RATE_HOURS = 4L
        const val FETCH_LATEST_FOODS_TASK = "FetchLatestFoodsTask"
         const val TAG_FETCH_LATEST_FOODS = "FetchLatestFoodsTaskTag"
    }
    fun fetchFoodsPeriodically(){
        val fetchFoodsRequest = PeriodicWorkRequestBuilder<RefreshLatestFoodWorker>(
            REFRESH_RATE_HOURS, TimeUnit.HOURS
        )
            .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)

                .build()
        )
            .addTag(TAG_FETCH_LATEST_FOODS)

        workManager.enqueueUniquePeriodicWork(
            FETCH_LATEST_FOODS_TASK,
            ExistingPeriodicWorkPolicy.KEEP,
            fetchFoodsRequest.build()
        )
    }
    fun cancelFetchingFoodsPeriodically(){
//        workManager.cancelAllWorkByTag(TAG_FETCH_LATEST_FOODS)
    }
    fun oneTimeRequest(ingredients : String){
        val oneTimeRequestConstraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val data = Data.Builder()
        data.putString("inputKey", ingredients)

        val sampleWork = OneTimeWorkRequest
            .Builder(RefreshLatestFoodWorker::class.java)
            .setInputData(data.build())
//            .setConstraints(oneTimeRequestConstraints)
            .build()

        workManager.enqueue(sampleWork)
    }
}