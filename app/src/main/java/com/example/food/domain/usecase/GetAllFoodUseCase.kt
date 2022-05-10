package com.example.food.domain.usecase

import com.example.food.data.model.allList.AllFoodList
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository

class GetAllFoodUseCase (
    private val foodRepository: FoodRepository
) {
    suspend fun execute():
            Resource<AllFoodList>{
        return foodRepository.getAllFoodRecep()
    }
}