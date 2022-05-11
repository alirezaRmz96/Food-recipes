package com.example.food.data.model.receFromId

data class RecepFromIdList(
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    val summary: String,
    val title: String,
)