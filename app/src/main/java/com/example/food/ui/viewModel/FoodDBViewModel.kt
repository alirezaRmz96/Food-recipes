package com.example.food.ui.viewModel

import androidx.lifecycle.*
import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDBViewModel @Inject constructor(
    private val foodRepository: FoodRepository
    ):ViewModel(){


    fun insert(recepFromIdList: RecepFromIdList) = viewModelScope.launch {
        foodRepository.insertFoodData(recepFromIdList)
    }

    //val flow:Flow<List<RecepFromIdList>> = foodRepository.getFoodDish()
    val allDishesList : LiveData<List<RecepFromIdList>> = foodRepository.getFoodDish().asLiveData()

    fun update(recepFromIdList: RecepFromIdList) = viewModelScope.launch {
        foodRepository.updateFavDishDetails(recepFromIdList)
    }

    fun delete(recepFromIdList: RecepFromIdList) = viewModelScope.launch {
        foodRepository.deleteFavDishDetails(recepFromIdList)
    }

    val favoriteDishes : LiveData<List<RecepFromIdList>> = foodRepository.getFavoriteDish().asLiveData()

//    fun getFoodFromDB() = liveData{
//        foodRepository.getFoodDish().collect{
//            emit(it)
//        }
//    }
}