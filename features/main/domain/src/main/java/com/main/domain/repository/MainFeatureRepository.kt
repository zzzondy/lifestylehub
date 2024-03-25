package com.main.domain.repository

import com.main.domain.models.UserLocation
import com.main.domain.models.results.ObtainingNearbyPlacesResult
import com.main.domain.models.results.ObtainingRandomTipResult
import com.main.domain.models.results.ObtainingUserWeatherResult

interface MainFeatureRepository {

    suspend fun getUserWeather(latitude: Double, longitude: Double): ObtainingUserWeatherResult

    suspend fun putLocationPermissionFlag(isRationaleShow: Boolean)

    suspend fun getLocationPermissionFlag(): Boolean

    suspend fun getNearbyPlaces(
        latitude: Double,
        longitude: Double,
        limit: Int,
        offset: Int,
    ): ObtainingNearbyPlacesResult

    fun obtainUserLocation(): UserLocation?

    suspend fun getRandomTip(): ObtainingRandomTipResult
}