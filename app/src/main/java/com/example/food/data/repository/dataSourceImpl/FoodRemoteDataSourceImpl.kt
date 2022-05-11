package com.example.food.data.repository.dataSourceImpl

import com.example.food.data.api.FoodApi
import com.example.food.data.model.allList.AllFoodList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.repository.dataSource.FoodRemoteDataSource
import retrofit2.Response

class FoodRemoteDataSourceImpl(
    private val foodApi: FoodApi
) : FoodRemoteDataSource {
    override suspend fun getAllFood(): Response<AllFoodList> {
        return foodApi.getAllFood()
    }

    override suspend fun getInformationFood(ingredients: String): Response<SpecialFood> {
        return foodApi.getSpecialFood(ingredients = ingredients)
    }

    override suspend fun getRecepFromId(id: Int): Response<RecepFromIdList> {
        return foodApi.getRecepFromId(id)
    }


}