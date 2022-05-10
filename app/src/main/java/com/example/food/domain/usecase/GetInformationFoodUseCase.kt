package com.example.food.domain.usecase

import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository

class GetInformationFoodUseCase(
    private val foodRepository: FoodRepository
) {
    suspend fun execute(ingredients:String):Resource<SpecialFood>{
        return foodRepository.getInformation(ingredients)
    }
}