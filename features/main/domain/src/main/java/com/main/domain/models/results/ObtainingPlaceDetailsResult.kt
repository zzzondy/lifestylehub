package com.main.domain.models.results

import com.main.domain.models.places.PlaceDetails

sealed class ObtainingPlaceDetailsResult {

    data class Success(val placeDetails: PlaceDetails) : ObtainingPlaceDetailsResult()

    data object Error : ObtainingPlaceDetailsResult()
}