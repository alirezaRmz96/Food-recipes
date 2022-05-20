package com.example.food.data.repository.dataSource

import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface FoodLocalDataSource {
    suspend fun insertFoodDishData(recepFromIdList: RecepFromIdList)
    fun getFoodDish():Flow<List<RecepFromIdList>>
    suspend fun updateFavDishDetails(recepFromIdList: RecepFromIdList)
    suspend fun deleteFavDishDetails(recepFromIdList: RecepFromIdList)
    fun getFavoriteDish():Flow<List<RecepFromIdList>>
}