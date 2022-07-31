package com.example.food.domain.usecase

import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.domain.repository.FoodRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFoodUseCase @Inject constructor(
    private val repository: FoodRepository
){
    /* notes ->
     1 use cases shouldn't contain mutable data
     2 in kotlin you can instance callable from useCase class as functions

    * */
    suspend operator fun invoke(ingredients: String) : List<AllFoodResultList>{
        return repository.getFood(ingredients)
    }
//    suspend fun getExecute(ingredients : String ) : List<AllFoodResultList> {
//        return repository.getFood(ingredients)
//    }
}