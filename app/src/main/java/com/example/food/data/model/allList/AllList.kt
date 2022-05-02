package com.example.food.data.model.allList

data class AllList(
    val number: Int,
    val offset: Int,
    val results: List<AllFoodResultList>,
    val totalResults: Int
)