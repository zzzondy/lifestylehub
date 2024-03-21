package com.feature_main.presentation.screens.place_details.state_hoisting

import com.common.ui.utils.UIText
import com.main.domain.models.places.PlaceDetails

sealed class PlaceDetailsScreenState {

    data object Loading : PlaceDetailsScreenState()

    data class Content(val placeDetails: PlaceDetails) : PlaceDetailsScreenState()

    data class Error(val message: UIText) : PlaceDetailsScreenState()
}