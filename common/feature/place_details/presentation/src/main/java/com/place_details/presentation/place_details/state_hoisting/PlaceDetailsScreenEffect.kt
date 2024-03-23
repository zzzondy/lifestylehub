package com.place_details.presentation.place_details.state_hoisting

sealed class PlaceDetailsScreenEffect {

    data object NavigateBack : PlaceDetailsScreenEffect()
}