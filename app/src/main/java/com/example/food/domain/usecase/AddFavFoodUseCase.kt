package com.example.food.domain.usecase

import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.domain.repository.FoodRepository

class AddFavFoodUseCase(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(recepFromIdUseCase: RecepFromIdList){
        repository.insertFoodData(recepFromIdUseCase)
    }
}
