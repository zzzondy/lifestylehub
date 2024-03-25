package com.main.utils.mappers

import com.main.data.remote.models.places.IconDetails
import com.main.data.remote.models.places.RemoteCategory
import com.main.data.remote.models.weather.MainWeatherData
import com.main.data.remote.models.weather.RemoteWeatherOnUserLocation
import com.main.data.utils.mappers.toDomain
import com.main.domain.models.places.Category
import com.main.domain.models.weather.WeatherOnUserLocation
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ExtensionsTest {

    @Test
    fun `should return domain category with the same fields and assemble photo url`() = runTest {
        val testingId = "testing id"
        val categoryName = "category_name"
        val urlPrefix = "url_start"
        val urlSuffix = "url_end"
        val remoteCategory = RemoteCategory(
            id = testingId,
            name = categoryName,
            iconDetails = IconDetails(
                prefix = urlPrefix,
                suffix = urlSuffix
            )
        )
        val expectedCategory = Category(
            id = testingId,
            name = categoryName,
            icon = urlPrefix + "120" + urlSuffix
        )

        Assert.assertEquals(expectedCategory, remoteCategory.toDomain())
    }

    @Test
    fun `should return domain weather on user location with the same fields`() = runTest {
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

        val expectedWeatherOnUserLocation = WeatherOnUserLocation(
            temperature,
            icon,
            minTemperature,
            maxTemperature,
            feelingTemperature,
            iconDescription,
            city
        )

        Assert.assertEquals(expectedWeatherOnUserLocation, inputWeather.toDomain())
    }
}