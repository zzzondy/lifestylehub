package com.place_details.presentation.place_details.state_hoisting

sealed class PlaceDetailsScreenEffect {

    data object NavigateBack : PlaceDetailsScreenEffect()

    data class NavigateToAddPlanScreen(val placeId: String, val placeName: String) :
        PlaceDetailsScreenEffect()
}