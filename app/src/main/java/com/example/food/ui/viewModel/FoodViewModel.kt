package com.example.food.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food.data.model.allList.AllList
import com.example.food.data.util.Resource
import com.example.food.domain.repository.IsNetWorking
import com.example.food.domain.usecase.GetAllFoodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class FoodViewModel(
    private val getAllFoodUseCase: GetAllFoodUseCase,
    private val isNetWorking : IsNetWorking
):ViewModel() {
    val foodLiveData : MutableLiveData<Resource<AllList>> = MutableLiveData()

    fun getAllFood() = viewModelScope.launch(Dispatchers.IO) {
        foodLiveData.postValue(Resource.Loading())
        try {
            if (isNetWorking.getNetWork()){
                val apiResult = getAllFoodUseCase.execute()
                foodLiveData.postValue(apiResult)
            }else{
                foodLiveData.postValue(Resource.Error("Internet no connect"))
            }
        }catch (e:Exception){
            foodLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }
}

/**
 * Error if i don't use view model factory see error like this
 *  Cannot create an instance of class com.example.food.ui.viewModel.FoodViewModel
 *  so what can i do?
 * */


class FoodViewModelFactory (
    private val getAllFoodUseCase: GetAllFoodUseCase,
    private val isNetWorking : IsNetWorking
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FoodViewModel(getAllFoodUseCase,isNetWorking) as T
    }

}