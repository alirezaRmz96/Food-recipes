package com.example.food.data.repository.dataSourceImpl

import com.example.food.data.api.FoodApi
import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.repository.dataSource.FoodRemoteDataSource
import retrofit2.Response

class FoodRemoteDataSourceImpl(
    private val foodApi: FoodApi
) : FoodRemoteDataSource {

    override suspend fun getInformationFood(ingredients: String): Response<List<AllFoodResultList>> {
        return foodApi.getAllFood(ingredients = ingredients)
    }

    override suspend fun getRecepFromId(id: Int): Response<RecepFromIdList> {
        return foodApi.getRecepFromId(id)
    }


}