package com.feature_main.presentation.screens.place_details.state_hoisting

sealed class PlaceDetailsScreenAction {

    data object OnTryAgainClicked : PlaceDetailsScreenAction()

    data object OnBackButtonClicked : PlaceDetailsScreenAction()
}