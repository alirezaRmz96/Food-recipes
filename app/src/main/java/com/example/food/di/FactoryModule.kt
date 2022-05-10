package com.example.food.di

import com.example.food.domain.repository.IsNetWorking
import com.example.food.domain.usecase.GetAllFoodUseCase
import com.example.food.domain.usecase.GetInformationFoodUseCase
import com.example.food.ui.viewModel.FoodViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideFoodViewModelFactory(
        getAllFoodUseCase: GetAllFoodUseCase,
        getInformationFoodUseCase: GetInformationFoodUseCase,
        isNetWorking: IsNetWorking
    ): FoodViewModelFactory{
        return FoodViewModelFactory(
            getAllFoodUseCase,getInformationFoodUseCase,isNetWorking
        )

    }
}