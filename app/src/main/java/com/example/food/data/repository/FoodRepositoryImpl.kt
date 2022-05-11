package com.example.food.data.repository

import com.example.food.data.model.allList.AllFoodList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.repository.dataSource.FoodRemoteDataSource
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository
import retrofit2.Response

class FoodRepositoryImpl(
    private val foodRemoteDataSource: FoodRemoteDataSource
) :FoodRepository{
    override suspend fun getAllFoodRecep(): Resource<AllFoodList> {
        return responseToResource(
            foodRemoteDataSource.getAllFood()
        )
    }

    override suspend fun getInformation(ingredients: String): Resource<SpecialFood> {
        return responseToResource1(
            foodRemoteDataSource.getInformationFood(ingredients)
        )
    }

    override suspend fun getRecepFromID(id: Int): Resource<RecepFromIdList> {
        return responseToResource2(
            foodRemoteDataSource.getRecepFromId(id)
        )
    }

    // this have problem you muse use one just two
    private fun responseToResource(response : Response<AllFoodList>):Resource<AllFoodList>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResource1(response : Response<SpecialFood>):Resource<SpecialFood>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResource2(response : Response<RecepFromIdList>):Resource<RecepFromIdList>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}