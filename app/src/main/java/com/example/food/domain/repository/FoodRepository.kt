package com.example.food.domain.repository

import com.example.food.data.model.allList.AllFoodList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.util.Resource

interface FoodRepository {
    suspend fun getAllFoodRecep(): Resource<AllFoodList>
    suspend fun getInformation(ingredients:String):Resource<SpecialFood>
    suspend fun getRecepFromID(id:Int):Resource<RecepFromIdList>
}