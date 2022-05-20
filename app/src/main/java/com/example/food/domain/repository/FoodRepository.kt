package com.example.food.domain.repository

import com.example.food.data.model.allList.AllFoodList
import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    suspend fun getAllFoodRecep(): Resource<AllFoodList>
    suspend fun getInformation(ingredients:String):Resource<SpecialFood>
    suspend fun getRecepFromID(id:Int):Resource<RecepFromIdList>
    suspend fun insertFoodData(recepFromIdList: RecepFromIdList)
    fun getFoodDish():Flow<List<RecepFromIdList>>
    fun getFavoriteDish():Flow<List<RecepFromIdList>>
    suspend fun updateFavDishDetails(recepFromIdList: RecepFromIdList)
    suspend fun deleteFavDishDetails(recepFromIdList: RecepFromIdList)

}