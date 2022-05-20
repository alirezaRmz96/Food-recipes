package com.example.food.data.model.receFromId

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_dish_table")
data class RecepFromIdList(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "image_type") val imageType: String,
    @ColumnInfo(name = "instructions") val instructions: String,
    @ColumnInfo(name = "price_Per_Serving") val pricePerServing: Double,
    @ColumnInfo(name = "ready_In_Minutes") val readyInMinutes: Int,
    @ColumnInfo(name = "servings") val servings: Int,
    @ColumnInfo(name = "source_Name") val sourceName: String,
    @ColumnInfo(name = "source_Url") val sourceUrl: String,
    @ColumnInfo(name = "spoonacular_Source_Url") val spoonacularSourceUrl: String,
    @ColumnInfo(name = "summary")  val summary: String,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "favorite_dish") var favoriteDish: Boolean = false
)