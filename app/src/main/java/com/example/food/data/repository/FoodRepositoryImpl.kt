package com.example.food.data.repository

import com.example.food.data.model.allList.AllList
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository
import retrofit2.Response

class FoodRepositoryImpl(
    private val foodRemoteDataSource: FoodRemoteDataSource
) :FoodRepository{
    override suspend fun getAllFoodRecep(): Resource<AllList> {
        return responseToResource(
            foodRemoteDataSource.getAllFood()
        )
    }

    private fun responseToResource(response : Response<AllList>):Resource<AllList>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}