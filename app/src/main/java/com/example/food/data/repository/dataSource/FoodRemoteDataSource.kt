package com.example.food.data.repository.dataSource

import com.example.food.data.model.allList.AllFoodList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import retrofit2.Response

interface FoodRemoteDataSource {
    suspend fun getAllFood():Response<AllFoodList>
    suspend fun getInformationFood(ingredients:String):Response<SpecialFood>
    suspend fun getRecepFromId(id:Int):Response<RecepFromIdList>
}