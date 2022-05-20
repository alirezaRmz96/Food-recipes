package com.example.food.data.repository.dataSourceImpl

import com.example.food.data.db.FoodDao
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.repository.dataSource.FoodLocalDataSource
import com.example.food.data.util.Resource
import kotlinx.coroutines.flow.Flow

class FoodLocalDataSourceImpl(
    private val foodDao: FoodDao
) :FoodLocalDataSource{
    override suspend fun insertFoodDishData(recepFromIdList: RecepFromIdList) {
        foodDao.insertFavDishDetails(recepFromIdList)
    }

    override fun getFoodDish(): Flow<List<RecepFromIdList>> {
        return foodDao.getFoodDish()
    }

    override suspend fun updateFavDishDetails(recepFromIdList: RecepFromIdList) {
        foodDao.updateFavDishDetails(recepFromIdList)
    }

    override suspend fun deleteFavDishDetails(recepFromIdList: RecepFromIdList) {
        foodDao.deleteFavDishDetails(recepFromIdList)
    }

    override fun getFavoriteDish(): Flow<List<RecepFromIdList>> {
        return foodDao.getFavoriteDish()
    }
}