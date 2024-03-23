package com.place_details.data.remote.repository

import com.place_details.data.remote.result.RemoteObtainingPlaceDetailsResult
import com.place_details.data.remote.result.RemoteObtainingPlacePhotosResult

interface RemotePlaceDetailsFeatureRepository {

    suspend fun getPlaceDetails(id: String): RemoteObtainingPlaceDetailsResult

    suspend fun getPlacePhotos(id: String): RemoteObtainingPlacePhotosResult
}