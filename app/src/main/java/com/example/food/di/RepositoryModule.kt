package com.example.food.di


import com.example.food.data.repository.dataSource.FoodRemoteDataSource
import com.example.food.data.repository.FoodRepositoryImpl
import com.example.food.data.repository.dataSource.FoodLocalDataSource
import com.example.food.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideFoodRepository(
        foodRemoteDataSource: FoodRemoteDataSource,
        foodLocalDataSource: FoodLocalDataSource,

    ):FoodRepository{
        return FoodRepositoryImpl(foodRemoteDataSource,foodLocalDataSource)
    }
    @Singleton
    @Provides
    fun providesCoroutineScope():CoroutineScope{
        // Run this code when providing an instance of CoroutineScope
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }
}