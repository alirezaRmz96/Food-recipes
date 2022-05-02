package com.example.food.di

import com.example.food.data.api.FoodApi
import com.example.food.data.repository.FoodRemoteDataSource
import com.example.food.data.repository.FoodRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideFoodRemoteDataSource(
        foodApi: FoodApi
    ):FoodRemoteDataSource{
        return FoodRemoteDataSourceImpl(foodApi)
    }
}