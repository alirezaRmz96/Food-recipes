package com.example.food.di

import com.example.food.data.repository.FoodRemoteDataSource
import com.example.food.data.repository.FoodRepositoryImpl
import com.example.food.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideFoodRepository(
        foodRemoteDataSource: FoodRemoteDataSource
    ):FoodRepository{
        return FoodRepositoryImpl(foodRemoteDataSource)
    }
}