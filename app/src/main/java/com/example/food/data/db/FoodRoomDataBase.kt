package com.example.food.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.food.data.model.receFromId.RecepFromIdList

@Database(entities = [RecepFromIdList::class], version = 1)
abstract class FoodRoomDataBase : RoomDatabase() {

    abstract  fun foodDao():FoodDao

    /**
    companion object{
        @Volatile
        private var INSTANCE : FoodRoomDataBase? = null
        fun getDataBase(context: Context):FoodRoomDataBase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRoomDataBase::class.java,
                    "fav_dish_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }*/

}