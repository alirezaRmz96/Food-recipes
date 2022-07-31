package com.example.food.data.repository.workManager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters

class RefreshLatestFoodWorker(
//    private val foodRepositoryImpl: FoodRepositoryImpl,
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

        override suspend fun doWork(): Result = try {
        val inputValue = inputData.getString("inputKey")
        Log.i("MyTAG", "do Work: $inputValue")
        Log.e("MyTAG", "do Work: $inputValue")
//        testClass.doNot()
        Result.success()
    } catch (error: Throwable) {
            Log.i("MyTAG", "do error Work: ${error.message}")
            Log.e("MyTAG", "do error Work: ${error.message}")
        Result.failure(createOutputData())
    }

    private fun createOutputData(): Data {
        return Data.Builder()
            .putString("outputKey", "Output Value")
            .build()
    }

//    override fun doWork(): Result {
//        val inputValue = inputData.getString("inputKey")
//        Log.i("MyTAG", "do Work: $inputValue")
//        Log.e("MyTAG", "do Work: $inputValue")
//        return Result.success()
//    }
}