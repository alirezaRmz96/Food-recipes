package com.example.food.domain.usecase

data class MovieUseCase (
    val getInformationFoodUseCase: GetInformationFoodUseCase,
    val getRecepFromIdUseCase: GetRecepFromIdUseCase,
    val deleteMovieUseCase: DeleteMovieUseCase,
    val addFavFoodUseCase: AddFavFoodUseCase,
    val getFoodFromDbUseCase : GetFoodFromDbUseCase
)
