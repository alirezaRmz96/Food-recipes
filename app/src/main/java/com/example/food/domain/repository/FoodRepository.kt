package com.example.food.domain.repository

import com.example.food.data.model.allList.AllList
import com.example.food.data.util.Resource

interface FoodRepository {
    suspend fun getAllFoodRecep(): Resource<AllList>
}