package com.example.food.data.model.allList
import java.io.Serializable

data class AllFoodResultList(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
) : Serializable