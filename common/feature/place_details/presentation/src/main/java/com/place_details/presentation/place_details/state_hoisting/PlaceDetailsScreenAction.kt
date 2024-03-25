package com.place_details.presentation.place_details.state_hoisting

sealed class PlaceDetailsScreenAction {

    data object OnTryAgainClicked : PlaceDetailsScreenAction()

    data object OnBackButtonClicked : PlaceDetailsScreenAction()

    data object OnAddPlanButtonClicked : PlaceDetailsScreenAction()
}