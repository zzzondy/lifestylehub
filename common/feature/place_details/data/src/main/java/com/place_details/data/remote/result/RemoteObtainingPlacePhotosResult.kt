package com.place_details.data.remote.result

import com.place_details.data.remote.models.PhotoDetails

sealed class RemoteObtainingPlacePhotosResult {

    data class Success(val photos: List<PhotoDetails>) : RemoteObtainingPlacePhotosResult()

    data object Error : RemoteObtainingPlacePhotosResult()
}