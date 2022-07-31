package com.example.food.data.db

import androidx.room.*
import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert
    suspend fun insertFavDishDetails(recepFromIdList: RecepFromIdList)

    // there we change it to list of it
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(recepFromIdList: List<AllFoodResultList>)

    @Query("SELECT * FROM food ORDER BY  idd")
    suspend fun  getFood():List<AllFoodResultList>

    @Query("SELECT * FROM FAV_DISH_TABLE ORDER BY ID")
    fun getFoodDish(): Flow<List<RecepFromIdList>>

    @Update
    suspend fun updateFavDishDetails(recepFromIdList: RecepFromIdList)

    @Delete
    suspend fun deleteFavDishDetails(recepFromIdList: RecepFromIdList)

    @Query("SELECT * FROM fav_dish_table WHERE favorite_dish = 1")
    fun getFavoriteDish():Flow<List<RecepFromIdList>>
}