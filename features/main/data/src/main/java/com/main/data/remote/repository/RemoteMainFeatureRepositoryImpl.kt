package com.main.data.remote.repository

import android.Manifest
import androidx.annotation.RequiresPermission
import com.common.network.location.GetCurrentUserLocationUseCase
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
                    val result = mainFeatureNetworkService.obtainUserWeatherByLocation(
                        location.latitude,
                        location.longitude,
                        "9626822fe842569ada7ecfb5c222cb17",
                        "ru",
                        "metric"
                    ).body()

                    if (result != null) {
                        RemoteObtainingUserWeatherResult.Success(result)
                    } else {
                        RemoteObtainingUserWeatherResult.NetworkError
                    }
                } else {
                    RemoteObtainingUserWeatherResult.LocationError
                }
            }
}