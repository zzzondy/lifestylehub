package com.place_details.presentation.place_details.state_hoisting

import com.common.ui.utils.UIText
import com.place_details.domain.models.places.PlaceDetails

sealed class PlaceDetailsScreenState {

    data object Loading : PlaceDetailsScreenState()

    data class Content(val placeDetails: PlaceDetails) : PlaceDetailsScreenState()

    data class Error(val message: UIText) : PlaceDetailsScreenState()
}