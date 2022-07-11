package com.example.food.ui.viewModel

import com.example.food.data.model.receFromId.RecepFromIdList

sealed class FoodEvent {
    data class DeleteDish(val recepFromIdList: RecepFromIdList):FoodEvent()
    data class  SaveFavFood (val recepFromIdList: RecepFromIdList):FoodEvent()
}