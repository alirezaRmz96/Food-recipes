package com.example.food.di

import android.app.Application
import androidx.room.Room
import com.example.food.data.db.FoodDao
import com.example.food.data.db.FoodRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideFoodDatabase(app:Application):FoodRoomDataBase{
        return Room.databaseBuilder(
            app,
            FoodRoomDataBase::class.java,
        "fav_dish_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodDao(foodRoomDataBase: FoodRoomDataBase):FoodDao{
        return foodRoomDataBase.foodDao()
    }
}