package com.example.food.data.repository

import com.example.food.data.model.allList.AllList
import retrofit2.Response

interface FoodRemoteDataSource {
    suspend fun getAllFood():Response<AllList>
}