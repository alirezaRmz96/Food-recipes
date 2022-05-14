package com.example.food.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food.data.model.allList.AllFoodList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.util.Resource
import com.example.food.domain.repository.IsNetWorking
import com.example.food.domain.usecase.GetAllFoodUseCase
import com.example.food.domain.usecase.GetInformationFoodUseCase
import com.example.food.domain.usecase.GetRecepFromIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Error if i don't use view model factory see error like this
 *  Cannot create an instance of class com.example.food.ui.viewModel.FoodViewModel
 *  so what can i do?
 * */

/**
 *  the line 1 until 3 getting debug and show that they work well
 *  but still has question which is still one load from network or no?
 * */

class FoodViewModel(
    private val getAllFoodUseCase: GetAllFoodUseCase,
    private val getInformationFoodUseCase: GetInformationFoodUseCase,
//    private val isNetWorking : IsNetWorking,
    private val getRecepFromIdUseCase: GetRecepFromIdUseCase
):ViewModel() {
    val foodLiveData : MutableLiveData<Resource<AllFoodList>> = MutableLiveData()
    val foodInformationLiveData : MutableLiveData<Resource<SpecialFood>> = MutableLiveData()
    val foodRecepFromID : MutableLiveData<Resource<RecepFromIdList>> = MutableLiveData()
    private var lastString = ""

    fun getAllFood() = viewModelScope.launch() {
        foodLiveData.postValue(Resource.Loading())
        try {
//            if (isNetWorking.getNetWork()){
                //1
                if (lastString.isEmpty()) {
                    //2
                    val apiResult = getAllFoodUseCase.execute()
                    foodLiveData.postValue(apiResult)
                } else
                    getInformationFood(lastString)
//            }else{
//                foodLiveData.postValue(Resource.Error("Internet no connect"))
//            }
        }catch (e:Exception){
            foodLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getInformationFood(ingredients : String) = viewModelScope.launch(Dispatchers.IO) {
        foodInformationLiveData.postValue(Resource.Loading())
        try {
//            if (isNetWorking.getNetWork()){
                //3
                val apiResult = getInformationFoodUseCase.execute(ingredients)
                lastString = ingredients
                foodInformationLiveData.postValue(apiResult)
//            }else{
//                foodInformationLiveData.postValue(Resource.Error("Internet no connect"))
//            }
        }catch (e:Exception){
            foodInformationLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getRecepFromId(id:Int) = viewModelScope.launch {
        foodRecepFromID.postValue(Resource.Loading())
        try {
//            if (isNetWorking.getNetWork()){
                val apiResult = getRecepFromIdUseCase.execute(id)
                foodRecepFromID.postValue(apiResult)
//            }
//            else {
//                foodRecepFromID.postValue(Resource.Error("Internet no connect"))
//            }
        }catch (e:Exception){
            foodRecepFromID.postValue(Resource.Error(e.message.toString()))
        }
    }
}

class FoodViewModelFactory (
    private val getAllFoodUseCase: GetAllFoodUseCase,
    private val getInformationFoodUseCase: GetInformationFoodUseCase,
//    private val isNetWorking : IsNetWorking,
    private val getRecepFromIdUseCase: GetRecepFromIdUseCase
):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodViewModel(getAllFoodUseCase,getInformationFoodUseCase,getRecepFromIdUseCase) as T
    }
}
