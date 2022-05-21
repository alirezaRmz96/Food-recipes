package com.example.food.ui.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.food.data.model.allList.AllFoodList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.util.Resource
import com.example.food.domain.repository.FoodRepository
import com.example.food.domain.repository.IsNetWorking
import com.example.food.domain.usecase.GetAllFoodUseCase
import com.example.food.domain.usecase.GetInformationFoodUseCase
import com.example.food.domain.usecase.GetRecepFromIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
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
    private val getRecepFromIdUseCase: GetRecepFromIdUseCase,
    private val foodRepository: FoodRepository
) : ViewModel() {
    val foodLiveData: MutableLiveData<Resource<AllFoodList>> = MutableLiveData()
    val foodInformationLiveData: MutableLiveData<Resource<SpecialFood>> = MutableLiveData()
    val foodRecepFromID: MutableLiveData<RecepFromIdList> = MutableLiveData()

    private var lastString = ""
    val loadRandomDish = MutableLiveData<Boolean>()

    fun getAllFood() = viewModelScope.launch() {
        foodLiveData.postValue(Resource.Loading())
        try {
//            if (isNetWorking.getNetWork()){
            //1
            if (lastString == "All" || lastString.isEmpty()) {
                //2
                val apiResult = getAllFoodUseCase.execute()
                foodLiveData.postValue(apiResult)
            } else
                getInformationFood(lastString)
//            }else{
//                foodLiveData.postValue(Resource.Error("Internet no connect"))
//            }
        } catch (e: Exception) {
            foodLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getInformationFood(ingredients: String) = viewModelScope.launch(Dispatchers.IO) {
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
        } catch (e: Exception) {
            foodInformationLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun insert(recepFromIdList: RecepFromIdList) = viewModelScope.launch {
        foodRepository.insertFoodData(recepFromIdList)
    }

    val allDishesList: LiveData<List<RecepFromIdList>> = foodRepository.getFoodDish().asLiveData()

    fun getRecepFromId(id: Int) = viewModelScope.launch {
        //foodRecepFromID.postValue(Resource.Loading())
        loadRandomDish.value = true
        try {
//            if (isNetWorking.getNetWork()){
            val apiResult = getRecepFromIdUseCase.execute(id)
            foodRecepFromID.postValue(apiResult.data!!)
            val list = allDishesList.value?.map { recep ->
                Log.d("TAG", "getRecepFromId: " + recep)
                if (recep.id == apiResult.data!!.id) {
                    Log.d("TAG", "show recep id : " + recep.id)
                    foodRecepFromID.postValue(recep)
                }
            }

            foodRecepFromID.let {
                loadRandomDish.value = false
            }
        } catch (e: Exception) {
            //foodRecepFromID.postValue(Resource.Error(e.message.toString()))
        }
    }
}

class FoodViewModelFactory(
    private val getAllFoodUseCase: GetAllFoodUseCase,
    private val getInformationFoodUseCase: GetInformationFoodUseCase,
//    private val isNetWorking : IsNetWorking,
    private val getRecepFromIdUseCase: GetRecepFromIdUseCase,
    private val foodRepository: FoodRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodViewModel(
            getAllFoodUseCase,
            getInformationFoodUseCase,
            getRecepFromIdUseCase,
            foodRepository
        ) as T
    }
}
