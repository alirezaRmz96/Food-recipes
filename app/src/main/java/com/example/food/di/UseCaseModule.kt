package com.example.food.di

import com.example.food.domain.repository.FoodRepository
import com.example.food.domain.usecase.GetAllFoodUseCase
import com.example.food.domain.usecase.GetInformationFoodUseCase
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
    @Singleton
    @Provides
    fun provideGetInformationFood(
        foodRepository: FoodRepository
    ):GetInformationFoodUseCase{
        return GetInformationFoodUseCase(foodRepository)
    }
}