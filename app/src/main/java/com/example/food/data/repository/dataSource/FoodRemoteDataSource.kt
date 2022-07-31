package com.example.food.data.repository.dataSource

import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import retrofit2.Response

interface FoodRemoteDataSource {
    suspend fun getInformationFood(ingredients:String):Response<List<AllFoodResultList>>
    suspend fun getRecepFromId(id:Int):Response<RecepFromIdList>
}