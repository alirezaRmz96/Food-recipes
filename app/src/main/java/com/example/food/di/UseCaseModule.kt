package com.example.food.di

import com.example.food.domain.repository.FoodRepository
import com.example.food.domain.usecase.*
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
    fun provideFoodUseCases(repository: FoodRepository):MovieUseCase{
        return MovieUseCase(
            getRecepFromIdUseCase = GetRecepFromIdUseCase(repository),
            deleteMovieUseCase = DeleteMovieUseCase(repository),
            addFavFoodUseCase = AddFavFoodUseCase(repository),
            getFoodFromDbUseCase = GetFoodFromDbUseCase(repository),
            getFoodUseCase = GetFoodUseCase(repository)
        )
    }
}