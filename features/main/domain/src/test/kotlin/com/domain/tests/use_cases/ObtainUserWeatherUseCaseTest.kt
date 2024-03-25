package com.domain.tests.use_cases

import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.models.weather.WeatherOnUserLocation
import com.main.domain.repository.MainFeatureRepository
import com.main.domain.use_cases.ObtainUserWeatherUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ObtainUserWeatherUseCaseTest {

    @Test
    fun `should return network error result`() = runTest {
        val dummyMainFeatureRepository = mockk<MainFeatureRepository>()
        val obtainUserWeatherUseCase = ObtainUserWeatherUseCase(dummyMainFeatureRepository)

        coEvery {
            dummyMainFeatureRepository.getUserWeather(
                1.0,
                1.0
            )
        } returns ObtainingUserWeatherResult.NetworkError

        Assert.assertEquals(
            obtainUserWeatherUseCase(1.0, 1.0),
            ObtainingUserWeatherResult.NetworkError
        )
    }

    @Test
    fun `should return success result`() = runTest {
        val dummyMainFeatureRepository = mockk<MainFeatureRepository>()
        val obtainUserWeatherUseCase = ObtainUserWeatherUseCase(dummyMainFeatureRepository)
        val iconDescription = "desc"
        val icon = "icon"
        val temperature = 10.0
        val feelingTemperature = 1.0
        val minTemperature = 0.0
        val maxTemperature = 10.0
        val city = "city"
        val expectedWeather = WeatherOnUserLocation(
            temperature,
            icon,
            minTemperature,
            maxTemperature,
            feelingTemperature,
            iconDescription,
            city
        )

        coEvery {
            dummyMainFeatureRepository.getUserWeather(
                1.0,
                1.0
            )
        } returns ObtainingUserWeatherResult.Success(
            expectedWeather
        )

        Assert.assertEquals(
            obtainUserWeatherUseCase(1.0, 1.0),
            ObtainingUserWeatherResult.Success(expectedWeather)
        )
    }
}