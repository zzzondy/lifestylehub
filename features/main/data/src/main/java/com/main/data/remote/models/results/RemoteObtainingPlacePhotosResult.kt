package com.main.data.remote.models.results

import com.main.data.remote.models.places.PhotoDetails

sealed class RemoteObtainingPlacePhotosResult {

    data class Success(val photos: List<PhotoDetails>) : RemoteObtainingPlacePhotosResult()

    data object Error : RemoteObtainingPlacePhotosResult()
}