package com.main.data.repository

import com.main.data.local.repository.LocalMainFeatureRepository
import com.main.data.remote.repository.RemoteMainFeatureRepository
import com.main.data.utils.mappers.toDomain
import com.main.domain.models.UserLocation
import com.main.domain.models.results.ObtainingNearbyPlacesResult
import com.main.domain.models.results.ObtainingRandomTipResult
import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.repository.MainFeatureRepository

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

    override suspend fun getRandomTip(): ObtainingRandomTipResult =
        remoteMainFeatureRepository.getRandomTip().toDomain()
}