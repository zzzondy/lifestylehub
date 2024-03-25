package com.main.data.remote.repository

import com.common.network.location.MapLocation
import com.main.data.remote.models.results.RemoteObtainingNearbyPlacesResult
import com.main.data.remote.models.results.RemoteObtainingRandomTipResult
import com.main.data.remote.models.results.RemoteObtainingUserWeatherResult

interface RemoteMainFeatureRepository {

    suspend fun getUserWeather(
        latitude: Double,
        longitude: Double
    ): RemoteObtainingUserWeatherResult

    suspend fun getPagedNearbyPlaces(
        latitude: Double,
        longitude: Double,
        limit: Int,
        offset: Int,
    ): RemoteObtainingNearbyPlacesResult

    fun obtainUserLocation(): MapLocation?

    suspend fun getRandomTip(): RemoteObtainingRandomTipResult
}