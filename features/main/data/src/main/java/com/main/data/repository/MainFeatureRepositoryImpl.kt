package com.main.data.repository

import android.util.Log
import com.main.data.local.repository.LocalMainFeatureRepository
import com.main.data.local.results.LocalObtainingPlaceDetailsResult
import com.main.data.remote.models.results.RemoteObtainingPlaceDetailsResult
import com.main.data.remote.models.results.RemoteObtainingPlacePhotosResult
import com.main.data.remote.repository.RemoteMainFeatureRepository
import com.main.data.utils.mappers.toDomain
import com.main.domain.models.UserLocation
import com.main.domain.models.results.ObtainingNearbyPlacesResult
import com.main.domain.models.results.ObtainingPlaceDetailsResult
import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.repository.MainFeatureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainFeatureRepositoryImpl(
    private val remoteMainFeatureRepository: RemoteMainFeatureRepository,
    private val localMainFeatureRepository: LocalMainFeatureRepository,
) : MainFeatureRepository {

    override suspend fun getUserWeather(
        latitude: Double,
        longitude: Double
    ): ObtainingUserWeatherResult =
        remoteMainFeatureRepository.getUserWeather(latitude, longitude).toDomain()

    override suspend fun putLocationPermissionFlag(isRationaleShow: Boolean) {
        localMainFeatureRepository.putLocationPermissionFlag(isRationaleShow)
    }

    override suspend fun getLocationPermissionFlag(): Boolean =
        localMainFeatureRepository.getLocationPermissionFlag()

    override suspend fun getNearbyPlaces(
        latitude: Double,
        longitude: Double,
        limit: Int,
        offset: Int,
    ): ObtainingNearbyPlacesResult =
        remoteMainFeatureRepository.getPagedNearbyPlaces(latitude, longitude, limit, offset)
            .toDomain()

    override fun obtainUserLocation(): UserLocation? =
        remoteMainFeatureRepository.obtainUserLocation()?.toDomain()

    override suspend fun getPlaceDetails(id: String): ObtainingPlaceDetailsResult {
        val localPlaceDetailsResult = localMainFeatureRepository.getPlaceDetails(id)
        if (localPlaceDetailsResult !is LocalObtainingPlaceDetailsResult.Success) {
            lateinit var placePhotosResult: RemoteObtainingPlacePhotosResult
            lateinit var placeDetailsResult: RemoteObtainingPlaceDetailsResult
            coroutineScope {
                launch(Dispatchers.IO) {
                    placePhotosResult = remoteMainFeatureRepository.getPlacePhotos(id)
                }

                launch(Dispatchers.IO) {
                    placeDetailsResult = remoteMainFeatureRepository.getPlaceDetails(id)
                }
            }

            val result = placeDetailsResult.toDomain(placePhotosResult)
            when (result) {
                is ObtainingPlaceDetailsResult.Success -> {
                    localMainFeatureRepository.insertPlaceDetails(result.placeDetails)
                }

                else -> {}
            }

            return result
        } else {
            return ObtainingPlaceDetailsResult.Success(
                placeDetails = localPlaceDetailsResult.placeDetails.toDomain()
            )
        }
    }
}