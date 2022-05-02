package com.example.food.data.repository

import com.example.food.data.api.FoodApi
import com.example.food.data.model.allList.AllList
import retrofit2.Response

class FoodRemoteDataSourceImpl(
    private val foodApi: FoodApi
) :FoodRemoteDataSource{
    override suspend fun getAllFood(): Response<AllList> {
        return foodApi.getAllFood()
    }
}