package com.feature_main.screens.main.components.content

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.common.ui.theme.LifestyleHubTheme
import com.feature_main.presentation.screens.main.components.content.MainScreenContentState
import com.feature_main.presentation.screens.main.components.content.WeatherWidgetTestingTags
import com.feature_main.presentation.screens.main.state_hoisting.NearbySectionState
import com.feature_main.presentation.screens.main.state_hoisting.RandomTipSectionState
import com.feature_main.presentation.screens.main.state_hoisting.WeatherSectionState
import com.main.domain.models.weather.WeatherOnUserLocation
import org.junit.Rule
import org.junit.Test

class MainScreenContentStateTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldDisplayLoadingWeatherWidget() {
        val weatherSectionState = WeatherSectionState.Loading
        val nearbySectionState = NearbySectionState.Loading
        val randomTipSectionState = RandomTipSectionState.Loading

        composeTestRule.setContent {
            LifestyleHubTheme {
                MainScreenContentState(
                    weatherSectionState = weatherSectionState,
                    nearbySectionState = nearbySectionState,
                    randomTipSectionState = randomTipSectionState,
                )
            }
        }

        composeTestRule.onNodeWithTag(WeatherWidgetTestingTags.LOADING_WEATHER_WIDGET)
            .assertIsDisplayed()
    }

    @Test
    fun shouldDisplayContentWeatherWidget() {
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
        val weatherSectionState = WeatherSectionState.Data(
            userWeather = expectedWeather
        )

        composeTestRule.setContent {
            LifestyleHubTheme {
                MainScreenContentState(
                    weatherSectionState = weatherSectionState,
                    nearbySectionState = NearbySectionState.Loading,
                    randomTipSectionState = RandomTipSectionState.Loading
                )
            }
        }

        composeTestRule.onNodeWithTag(WeatherWidgetTestingTags.temperature)
            .assertTextContains(temperature.toInt().toString(), substring = true)

        composeTestRule.onNodeWithTag(WeatherWidgetTestingTags.weatherCondition)
            .assertTextContains(iconDescription, substring = true)

        composeTestRule.onNodeWithTag(WeatherWidgetTestingTags.feelingTemperature)
            .assertTextContains(feelingTemperature.toInt().toString(), substring = true)

        composeTestRule.onNodeWithTag(WeatherWidgetTestingTags.city)
            .assertTextContains(city, substring = true)

        composeTestRule.onNodeWithTag(WeatherWidgetTestingTags.temperatureRange)
            .assertTextContains(minTemperature.toInt().toString(), substring = true)
            .assertTextContains(maxTemperature.toInt().toString(), substring = true)

        composeTestRule.onNodeWithTag(WeatherWidgetTestingTags.icon)
            .isDisplayed()
    }
}