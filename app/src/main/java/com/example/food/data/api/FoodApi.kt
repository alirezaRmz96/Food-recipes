package com.example.food.data.api

import com.example.food.BuildConfig
import com.example.food.data.model.allList.AllList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("complexSearch")
    suspend fun getAllFood(
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ):Response<AllList>

}