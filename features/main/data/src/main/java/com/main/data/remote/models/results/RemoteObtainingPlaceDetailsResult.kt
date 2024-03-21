package com.main.data.remote.models.results

import com.main.data.remote.models.places.PlaceDetailsVenue

sealed class RemoteObtainingPlaceDetailsResult {

    data class Success(val placeDetails: PlaceDetailsVenue) : RemoteObtainingPlaceDetailsResult()

    data object Error : RemoteObtainingPlaceDetailsResult()
}