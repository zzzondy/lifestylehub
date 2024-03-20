package com.main.data.remote.models.results

import com.main.data.remote.models.places.PlaceResponse

sealed class RemoteObtainingNearbyPlacesResult {

    data class Success(val remotePlaces: List<PlaceResponse>) :
        RemoteObtainingNearbyPlacesResult()

    data object Error : RemoteObtainingNearbyPlacesResult()
}