package com.example.food.data.db

import androidx.room.*
import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood

@Database(entities = [RecepFromIdList::class,AllFoodResultList::class], version = 1, exportSchema = false)
//@TypeConverters(Converter::class)
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