package com.place_details.data.remote.result

import com.place_details.data.remote.models.PlaceDetailsVenue

sealed class RemoteObtainingPlaceDetailsResult {

    data class Success(val placeDetails: PlaceDetailsVenue) : RemoteObtainingPlaceDetailsResult()

    data object Error : RemoteObtainingPlaceDetailsResult()
}