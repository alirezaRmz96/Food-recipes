package com.example.food.data.model.allList

data class AllFoodList(
    val number: Int,
    val offset: Int,
    val results: List<AllFoodResultList>,
    val totalResults: Int
)