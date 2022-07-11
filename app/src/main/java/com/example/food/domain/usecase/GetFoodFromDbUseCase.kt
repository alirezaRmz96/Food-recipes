package com.example.food.domain.usecase

import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetFoodFromDbUseCase(
    private val repository: FoodRepository
) {
    operator fun invoke(): Flow<List<RecepFromIdList>> {
        return repository.getFavoriteDish()
    }
}