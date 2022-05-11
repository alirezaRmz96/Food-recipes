package com.example.food.domain.usecase

import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRecepFromIdUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend fun execute(id:Int):Resource<RecepFromIdList>{
        return foodRepository.getRecepFromID(id)
    }
}