package com.example.food.di

import com.example.food.data.db.FoodDao
import com.example.food.data.repository.dataSource.FoodLocalDataSource
import com.example.food.data.repository.dataSourceImpl.FoodLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(
        foodDao: FoodDao
    ):FoodLocalDataSource{
        return FoodLocalDataSourceImpl(foodDao)
    }
}