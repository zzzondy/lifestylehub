package com.main.remote.repository

import com.common.network.location.LocationManager
import com.main.data.remote.models.results.RemoteObtainingUserWeatherResult
import com.main.data.remote.models.weather.MainWeatherData
import com.main.data.remote.models.weather.RemoteWeatherOnUserLocation
import com.main.data.remote.network.MainFeatureNetworkService
import com.main.data.remote.network.NearbyPlacesNetworkService
import com.main.data.remote.network.RandomTipNetworkService
import com.main.data.remote.repository.RemoteMainFeatureRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class RemoteMainFeatureRepositoryImplTest {

    @Test
    fun `should return network error when some error occurred`() = runTest {
        val dummyMainFeatureNetworkService = mockk<MainFeatureNetworkService>()
        val dummyLocationManager = mockk<LocationManager>()
        val dummyNearbyPlacesNetworkService = mockk<NearbyPlacesNetworkService>()
        val dummyRandomTipNetworkService = mockk<RandomTipNetworkService>()
        val dummyRemoteMainFeatureRepository =
            RemoteMainFeatureRepositoryImpl(
                dummyLocationManager,
                dummyMainFeatureNetworkService,
                dummyNearbyPlacesNetworkService,
                dummyRandomTipNetworkService
            )

        coEvery {
            dummyMainFeatureNetworkService.obtainUserWeatherByLocation(
                1.0,
                1.0,
            )
        } throws Exception()

        Assert.assertEquals(
            dummyRemoteMainFeatureRepository.getUserWeather(1.0, 1.0),
            RemoteObtainingUserWeatherResult.NetworkError
        )
    }

    @Test
    fun `should return network error when response is not successful`() = runTest {
        val dummyMainFeatureNetworkService = mockk<MainFeatureNetworkService>()
        val dummyLocationManager = mockk<LocationManager>()
        val dummyNearbyPlacesNetworkService = mockk<NearbyPlacesNetworkService>()
        val dummyRandomTipNetworkService = mockk<RandomTipNetworkService>()
        val dummyRemoteMainFeatureRepository =
            RemoteMainFeatureRepositoryImpl(
                dummyLocationManager,
                dummyMainFeatureNetworkService,
                dummyNearbyPlacesNetworkService,
                dummyRandomTipNetworkService
            )

        coEvery {
            dummyMainFeatureNetworkService.obtainUserWeatherByLocation(
                1.0,
                1.0,
            )
        } returns Response.error(400, "".toResponseBody(null))

        Assert.assertEquals(
            dummyRemoteMainFeatureRepository.getUserWeather(1.0, 1.0),
            RemoteObtainingUserWeatherResult.NetworkError
        )
    }

    @Test
    fun `should return success result`() = runTest {
        val dummyMainFeatureNetworkService = mockk<MainFeatureNetworkService>()
        val dummyLocationManager = mockk<LocationManager>()
        val dummyNearbyPlacesNetworkService = mockk<NearbyPlacesNetworkService>()
        val dummyRandomTipNetworkService = mockk<RandomTipNetworkService>()
        val dummyRemoteMainFeatureRepository =
            RemoteMainFeatureRepositoryImpl(
                dummyLocationManager,
                dummyMainFeatureNetworkService,
                dummyNearbyPlacesNetworkService,
                dummyRandomTipNetworkService,
            )
        val iconDescription = "desc"
        val icon = "icon"
        val temperature = 10.0
        val feelingTemperature = 1.0
        val minTemperature = 0.0
        val maxTemperature = 10.0
        val city = "city"
        val inputWeather = RemoteWeatherOnUserLocation(
            iconDetails = listOf(
                com.main.data.remote.models.weather.IconDetails(
                    description = iconDescription,
                    icon = icon,
                )
            ),
            mainWeatherData = MainWeatherData(
                temperature, feelingTemperature, minTemperature, maxTemperature
            ),
            currentCity = city
        )

        coEvery {
            dummyMainFeatureNetworkService.obtainUserWeatherByLocation(
                1.0,
                1.0,
            )
        } returns Response.success(
            inputWeather
        )

        Assert.assertEquals(
            dummyRemoteMainFeatureRepository.getUserWeather(1.0, 1.0),
            RemoteObtainingUserWeatherResult.Success(inputWeather)
        )
    }
}