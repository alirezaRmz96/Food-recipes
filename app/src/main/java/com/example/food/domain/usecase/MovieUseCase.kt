package com.example.food.domain.usecase

data class MovieUseCase (
    val getRecepFromIdUseCase: GetRecepFromIdUseCase,
    val deleteMovieUseCase: DeleteMovieUseCase,
    val addFavFoodUseCase: AddFavFoodUseCase,
    val getFoodFromDbUseCase : GetFoodFromDbUseCase,
    val  getFoodUseCase: GetFoodUseCase
)
