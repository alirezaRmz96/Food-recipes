package com.example.food.data.db

import androidx.room.*
import com.example.food.data.model.receFromId.RecepFromIdList
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert
    suspend fun insertFavDishDetails(recepFromIdList: RecepFromIdList)

    @Query("SELECT * FROM FAV_DISH_TABLE ORDER BY ID")
    fun getFoodDish(): Flow<List<RecepFromIdList>>

    @Update
    suspend fun updateFavDishDetails(recepFromIdList: RecepFromIdList)

    @Delete
    suspend fun deleteFavDishDetails(recepFromIdList: RecepFromIdList)

    @Query("SELECT * FROM fav_dish_table WHERE favorite_dish = 1")
    fun getFavoriteDish():Flow<List<RecepFromIdList>>
}