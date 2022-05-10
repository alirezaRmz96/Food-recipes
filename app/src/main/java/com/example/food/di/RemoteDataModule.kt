package com.example.food.di

import com.example.food.data.api.FoodApi
import com.example.food.data.repository.dataSource.FoodRemoteDataSource
import com.example.food.data.repository.dataSourceImpl.FoodRemoteDataSourceImpl
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
    ): FoodRemoteDataSource {
        return FoodRemoteDataSourceImpl(foodApi)
    }
}