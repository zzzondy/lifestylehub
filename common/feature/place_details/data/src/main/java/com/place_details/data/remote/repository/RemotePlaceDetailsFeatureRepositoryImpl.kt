package com.place_details.data.remote.repository

import com.place_details.data.remote.network.PlaceDetailsNetworkService
import com.place_details.data.remote.result.RemoteObtainingPlaceDetailsResult
import com.place_details.data.remote.result.RemoteObtainingPlacePhotosResult

class RemotePlaceDetailsFeatureRepositoryImpl(
    private val placeDetailsFeatureNetworkService: PlaceDetailsNetworkService
) : RemotePlaceDetailsFeatureRepository {

    override suspend fun getPlaceDetails(id: String): RemoteObtainingPlaceDetailsResult {
        return try {
            val result = placeDetailsFeatureNetworkService.getPlaceDetails(id)

            if (result.isSuccessful && result.body() != null) {
                RemoteObtainingPlaceDetailsResult.Success(
                    placeDetails = result.body()!!.response.venue
                )
            } else {
                RemoteObtainingPlaceDetailsResult.Error
            }
        } catch (e: Exception) {
            RemoteObtainingPlaceDetailsResult.Error
        }
    }

    override suspend fun getPlacePhotos(id: String): RemoteObtainingPlacePhotosResult {
        return try {
            val result = placeDetailsFeatureNetworkService.getPlacePhotos(id)

            if (result.isSuccessful && result.body() != null) {
                RemoteObtainingPlacePhotosResult.Success(
                    photos = result.body()!!.response.photos.items
                )
            } else {
                RemoteObtainingPlacePhotosResult.Error
            }
        } catch (e: Exception) {
            RemoteObtainingPlacePhotosResult.Error
        }
    }
}