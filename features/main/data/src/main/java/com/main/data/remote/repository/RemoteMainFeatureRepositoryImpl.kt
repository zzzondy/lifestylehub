package com.main.data.remote.repository

import android.Manifest
import androidx.annotation.RequiresPermission
import com.common.network.location.GetCurrentUserLocationUseCase
import com.main.data.BuildConfig
import com.main.data.remote.models.results.RemoteObtainingUserWeatherResult
import com.main.data.remote.network.MainFeatureNetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RemoteMainFeatureRepositoryImpl(
    private val getCurrentUserLocationUseCase: GetCurrentUserLocationUseCase,
    private val mainFeatureNetworkService: MainFeatureNetworkService,
) : RemoteMainFeatureRepository {

    @RequiresPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    override fun getUserWeather(): Flow<RemoteObtainingUserWeatherResult> =
        getCurrentUserLocationUseCase.fetchUpdates()
            .map { location ->
                if (location != null) {
                    try {
                        val result = mainFeatureNetworkService.obtainUserWeatherByLocation(
                            location.latitude,
                            location.longitude,
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
                } else {
                    RemoteObtainingUserWeatherResult.LocationError
                }
            }
}