package com.example.food.di

import android.content.Context
import com.example.food.domain.repository.IsNetWorking
import com.example.food.domain.usecase.NetWorkCheck
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CheckNetWork {
    @Singleton
    @Provides
    fun provideIsNetWorking(
        @ApplicationContext context: Context
    ):IsNetWorking{
        return NetWorkCheck(context)
    }
}