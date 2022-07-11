package com.example.food.data.api

import com.example.food.BuildConfig
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodApi {

    @GET("findByIngredients")
    suspend fun getSpecialFood(
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY,
        @Query("ingredients")
        ingredients:String,
        @Query("number")
        number:Int = 10
    ):Response<SpecialFood>

    @GET("{id}/information")
    suspend fun getRecepFromId(
        @Path("id") id : Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY,
    ):Response<RecepFromIdList>


}