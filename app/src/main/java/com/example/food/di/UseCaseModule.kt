package com.example.food.di

import com.example.food.domain.repository.FoodRepository
import com.example.food.domain.usecase.GetAllFoodUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetAllFoodRecep(
        foodRepository: FoodRepository
    ):GetAllFoodUseCase{
        return GetAllFoodUseCase(foodRepository)
    }
}