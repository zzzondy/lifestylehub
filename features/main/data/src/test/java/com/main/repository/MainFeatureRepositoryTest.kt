package com.main.repository

import com.main.data.local.repository.LocalMainFeatureRepository
import com.main.data.remote.models.results.RemoteObtainingUserWeatherResult
import com.main.data.remote.models.weather.IconDetails
import com.main.data.remote.models.weather.MainWeatherData
import com.main.data.remote.models.weather.RemoteWeatherOnUserLocation
import com.main.data.remote.repository.RemoteMainFeatureRepository
import com.main.data.repository.MainFeatureRepositoryImpl
import com.main.data.utils.mappers.toDomain
import com.main.domain.models.results.ObtainingUserWeatherResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MainFeatureRepositoryTest {

    @Test
    fun `should return network error result`() = runTest {
        val remoteMainFeatureRepository = mockk<RemoteMainFeatureRepository>()
        val localMainFeatureRepository = mockk<LocalMainFeatureRepository>()
        val mainFeatureRepository = MainFeatureRepositoryImpl(
            remoteMainFeatureRepository, localMainFeatureRepository
        )

        coEvery {
            remoteMainFeatureRepository.getUserWeather(
                1.0,
                1.0
            )
        } returns RemoteObtainingUserWeatherResult.NetworkError

        Assert.assertEquals(
            mainFeatureRepository.getUserWeather(1.0, 1.0),
            ObtainingUserWeatherResult.NetworkError
        )
    }

    @Test
    fun `should return success result`() = runTest {
        val remoteMainFeatureRepository = mockk<RemoteMainFeatureRepository>()
        val localMainFeatureRepository = mockk<LocalMainFeatureRepository>()
        val mainFeatureRepository = MainFeatureRepositoryImpl(
            remoteMainFeatureRepository, localMainFeatureRepository
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
                IconDetails(
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
            remoteMainFeatureRepository.getUserWeather(
                1.0,
                1.0
            )
        } returns RemoteObtainingUserWeatherResult.Success(
            inputWeather
        )

        Assert.assertEquals(
            mainFeatureRepository.getUserWeather(1.0, 1.0),
            ObtainingUserWeatherResult.Success(
                inputWeather.toDomain()
            )
        )
    }
}