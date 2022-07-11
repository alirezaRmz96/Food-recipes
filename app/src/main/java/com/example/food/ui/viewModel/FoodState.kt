package com.example.food.ui.viewModel

import com.example.food.data.model.receFromId.RecepFromIdList

data class FoodState (
    val food : List<RecepFromIdList> = emptyList(),
 )