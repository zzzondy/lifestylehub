package com.place_details.domain.models.results

import com.place_details.domain.models.places.PlaceDetails

sealed class ObtainingPlaceDetailsResult {

    data class Success(val placeDetails: PlaceDetails) : ObtainingPlaceDetailsResult()

    data object Error : ObtainingPlaceDetailsResult()
}