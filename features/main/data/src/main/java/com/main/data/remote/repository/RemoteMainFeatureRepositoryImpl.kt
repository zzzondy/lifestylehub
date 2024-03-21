package com.main.data.remote.repository

import com.common.network.location.LocationManager
import com.common.network.location.MapLocation
import com.main.data.BuildConfig
import com.main.data.remote.models.results.RemoteObtainingNearbyPlacesResult
import com.main.data.remote.models.results.RemoteObtainingPlaceDetailsResult
import com.main.data.remote.models.results.RemoteObtainingPlacePhotosResult
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
                BuildConfig.OPEN_WEATHER_API_KEY,
                "ru",
                "metric"
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

    override suspend fun getPlaceDetails(id: String): RemoteObtainingPlaceDetailsResult {
        return try {
            val result = nearbyPlacesNetworkService.getPlaceDetails(id)

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
            val result = nearbyPlacesNetworkService.getPlacePhotos(id)

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