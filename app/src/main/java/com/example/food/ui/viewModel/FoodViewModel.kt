package com.example.food.ui.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.food.data.model.allList.AllFoodResultList
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.util.Resource
import com.example.food.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {


    private var _foodRecepFromID = MutableSharedFlow<Resource<RecepFromIdList>>()
    val foodRecepFromIDFlow = _foodRecepFromID.asSharedFlow()

    /*
    we write like below for
    it conveys the relationship between the emission of the state holder and its associated screen or UI element
    * */
    private val _food =
        MutableStateFlow<Resource<List<AllFoodResultList>>>(Resource.Success(emptyList()))
    val foodSpe: StateFlow<Resource<List<AllFoodResultList>>> = _food.asStateFlow()

    val errorMessage = MutableLiveData<String>()

    var getFoodAPIJob: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

//    private val _food1 = MutableStateFlow<AllFoodResultList>(AllFoodResultList(0,"","",""))
//    val food: StateFlow<AllFoodResultList> = _food1.asStateFlow()


    init {
        getFoodFromDB()
        getFoodFromAPI()
    }
    fun getFoodFromAPi(ingredients: String = "Caramel"){
        getFoodAPIJob = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = movieUseCase.getFoodUseCase(ingredients)
            withContext(Dispatchers.Main){
                if (response.isNotEmpty()){
                    _food.emit(Resource.Success(response))
                    loading.value = false
                }else{
                    onError("Error : SOme THIng bad happened")
                }
            }
        }
    }
    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }
    override fun onCleared() {
        super.onCleared()
        getFoodAPIJob?.cancel()
    }

    fun getFoodFromAPI(ingredients: String = "Caramel") = viewModelScope.launch {
        _food.emit(Resource.Loading())
        try {

//            FoodsTasksDataSource(WorkManager.getInstance(context)).oneTimeRequest(ingredients)

            val food = movieUseCase.getFoodUseCase(ingredients)
            _food.emit(Resource.Success(food))

        } catch (e: Exception) {
            _food.emit(Resource.Error(e.message.toString()))
        }
    }

    // ********** getting from api ***********

    fun getRecepFromId(id: Int) = viewModelScope.launch {

        _foodRecepFromID.emit(Resource.Loading())
        try {
            val apiResult = movieUseCase.getRecepFromIdUseCase.execute(id)
            _foodRecepFromID.emit(apiResult)
            state.value.food.map { recep ->
                Log.d("TAG", "getRecepFromId: $recep")
                if (recep.id == apiResult.data?.id) {
                    Log.d("TAG", "show recep id : " + recep.id)
                    _foodRecepFromID.emit(Resource.Success(recep))
                }
            }


        } catch (e: Exception) {
            _foodRecepFromID.emit(Resource.Error(e.message.toString()))
        }
    }

    // *********** getting from db

    private var getFoodDBJob: Job? = null

    private val _state = mutableStateOf(FoodState())
    val state: State<FoodState> = _state

    private var recentlyDeletedMovie: RecepFromIdList? = null

    fun onEvent(event: FoodEvent) {
        when (event) {
            is FoodEvent.DeleteDish -> {
                viewModelScope.launch {
                    movieUseCase.deleteMovieUseCase(event.recepFromIdList)
                    recentlyDeletedMovie = event.recepFromIdList
                }
            }
            is FoodEvent.SaveFavFood -> {
                viewModelScope.launch {
                    try {
                        movieUseCase.addFavFoodUseCase(
                            event.recepFromIdList
                        )
//                        _eventFlow.emit(UiEvent.SaveFavDish)
                    } catch (e: Exception) {
                        UiEvent.ShowToast(
                            message = e.message ?: "Couldn't save food"
                        )
                    }
                }
            }
        }
    }

    private fun getFoodFromDB() {
        getFoodDBJob?.cancel()
        getFoodDBJob = movieUseCase.getFoodFromDbUseCase().onEach { foods ->
            _state.value = state.value.copy(
                food = foods
            )
        }
            .launchIn(viewModelScope)
    }


    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
        object SaveFavDish : UiEvent()
    }
}




