package com.main.data.remote.repository

import com.common.network.location.LocationManager
import com.common.network.location.MapLocation
import com.main.data.BuildConfig
import com.main.data.remote.models.results.RemoteObtainingNearbyPlacesResult
import com.main.data.remote.models.results.RemoteObtainingUserWeatherResult
import com.main.data.remote.network.MainFeatureNetworkService
import com.main.data.remote.network.NearbyPlacesNetworkService

class RemoteMainFeatureRepositoryImpl(
    private val locationManager: LocationManager,
    private val mainFeatureNetworkService: MainFeatureNetworkService,
    private val nearbyPlacesNetworkService: NearbyPlacesNetworkService,
) : RemoteMainFeatureRepository {

    override fun obtainUserLocation(): MapLocation? =
        locationManager.getCurrentLocation()

    override suspend fun getUserWeather(
        latitude: Double,
        longitude: Double
    ): RemoteObtainingUserWeatherResult {
        return try {
            val result = mainFeatureNetworkService.obtainUserWeatherByLocation(
                latitude,
                longitude,
            )

            if (result.isSuccessful && result.body() != null) {
                RemoteObtainingUserWeatherResult.Success(result.body()!!)
            } else {
                RemoteObtainingUserWeatherResult.NetworkError
            }
        } catch (e: Exception) {
            RemoteObtainingUserWeatherResult.NetworkError
        }
    }

    override suspend fun getPagedNearbyPlaces(
        latitude: Double,
        longitude: Double,
        limit: Int,
        offset: Int,
    ): RemoteObtainingNearbyPlacesResult {
        return try {
            val placesResult = nearbyPlacesNetworkService.getNearbyPlaces(
                latitudeWithLongitude = "${latitude},${longitude}",
                limit = limit,
                offset = offset
            )

            if (placesResult.isSuccessful && placesResult.body() != null) {
                RemoteObtainingNearbyPlacesResult.Success(
                    remotePlaces = placesResult.body()!!.response.group.results,
                )
            } else {
                RemoteObtainingNearbyPlacesResult.Error
            }
        } catch (e: Exception) {
            RemoteObtainingNearbyPlacesResult.Error
        }
    }
}