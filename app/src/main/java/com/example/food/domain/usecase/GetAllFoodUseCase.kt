package com.example.food.domain.usecase

import com.example.food.data.model.allList.AllList
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository

class GetAllFoodUseCase (
    private val foodRepository: FoodRepository
) {
    suspend fun execute():
            Resource<AllList>{
        return foodRepository.getAllFoodRecep()
    }
}