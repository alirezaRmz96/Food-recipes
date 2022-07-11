package com.example.food.ui.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.food.data.model.receFromId.RecepFromIdList
import com.example.food.data.model.specialFood.SpecialFood
import com.example.food.data.util.Resource
import com.example.food.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

    private var _foodInformation = MutableStateFlow<Resource<SpecialFood>>(Resource.Success(SpecialFood()))
    val foodInformationFlow = _foodInformation.asStateFlow()

    private var _foodRecepFromID = MutableSharedFlow<Resource<RecepFromIdList>>()
    val foodRecepFromIDFlow = _foodRecepFromID.asSharedFlow()



    init {
        getFoods()
        getInformationFood()
    }

    // ********** getting from api ***********

    fun getInformationFood(ingredients: String = "Caramel") = viewModelScope.launch(Dispatchers.IO) {
        _foodInformation.emit(Resource.Loading())
        try {
            val apiResult = movieUseCase.getInformationFoodUseCase.execute(ingredients)
            _foodInformation.emit(apiResult)

        } catch (e: Exception) {
            _foodInformation.emit(Resource.Error(e.message.toString()))
        }
    }

    fun getRecepFromId(id: Int) = viewModelScope.launch {

        _foodRecepFromID.emit(Resource.Loading())
        try {
            val apiResult = movieUseCase.getRecepFromIdUseCase.execute(id)
            _foodRecepFromID.emit(apiResult)
            state.value.food.map { recep ->
                Log.d("TAG", "getRecepFromId: " + recep)
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

    private var getFoodJob: Job? = null

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

    private fun getFoods() {
        getFoodJob?.cancel()
        getFoodJob = movieUseCase.getFoodFromDbUseCase().onEach { foods ->
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




