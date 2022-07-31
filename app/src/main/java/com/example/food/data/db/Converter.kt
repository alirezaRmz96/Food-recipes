package com.example.food.data.db

import androidx.room.TypeConverter
import com.example.food.data.model.allList.AllFoodResultList
import com.google.gson.Gson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converter {
//    @TypeConverter
//    fun listToJson (value: List<AllFoodResultList>?) = Gson().toJson(value)
//
//    @TypeConverter
//    fun  gsonToList(value : String) = Gson().fromJson(value,Array<AllFoodResultList>::class.java).toList()
//
//    @TypeConverter
//    fun fromString(stringListString: String): List<String> {
//        return stringListString.split(",").map { it }
//    }
//
//    @TypeConverter
//    fun toString(stringList: List<String>): String {
//        return stringList.joinToString(separator = ",")
//    }

    @TypeConverter
    fun fromList(value : ArrayList<AllFoodResultList>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<ArrayList<AllFoodResultList>>(value)
}