package com.feature_main.presentation.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.common.ui.theme.LifestyleHubTheme
import com.feature_main.presentation.BuildConfig
import com.feature_main.presentation.R

@Composable
fun WeatherWidget(
    temperature: Int,
    minTemperature: Int,
    maxTemperature: Int,
    feelingTemperature: Int,
    weatherCondition: String,
    city: String,
    modifier: Modifier = Modifier,
    icon: String,
) {
    val paddings = LifestyleHubTheme.paddings

    ElevatedCard(
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = ConstraintSet {
                val yourLocationText = createRefFor(WeatherWidgetLayoutIDs.title)
                val temperatureText = createRefFor(WeatherWidgetLayoutIDs.temperature)
                val cityText = createRefFor(WeatherWidgetLayoutIDs.city)
                val temperatureRange = createRefFor(WeatherWidgetLayoutIDs.temperatureRange)
                val temperatureToday = createRefFor(WeatherWidgetLayoutIDs.temperatureToday)
                val weatherConditionText = createRefFor(WeatherWidgetLayoutIDs.weatherCondition)
                val feelsLikeTemperature = createRefFor(WeatherWidgetLayoutIDs.feelingTemperature)
                val iconImage = createRefFor(WeatherWidgetLayoutIDs.icon)

                constrain(temperatureText) {
                    top.linkTo(parent.top, paddings.extraSmall)
                    end.linkTo(parent.end, paddings.extraSmall)
                }

                constrain(yourLocationText) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top, paddings.extraSmall)
                    start.linkTo(parent.start, paddings.extraSmall)
                    end.linkTo(temperatureText.start, paddings.extraSmall)
                }

                constrain(cityText) {
                    width = Dimension.fillToConstraints
                    top.linkTo(yourLocationText.bottom, paddings.extraSmall)
                    start.linkTo(parent.start, paddings.extraSmall)
                    end.linkTo(temperatureToday.start, paddings.extraSmall)
                }

                constrain(temperatureToday) {
                    top.linkTo(temperatureText.bottom, paddings.extraSmall)
                    end.linkTo(parent.end, paddings.extraSmall)
                }

                constrain(temperatureRange) {
                    top.linkTo(temperatureToday.bottom, paddings.extraSmall)
                    end.linkTo(parent.end, paddings.extraSmall)
                }

                constrain(weatherConditionText) {
                    width = Dimension.fillToConstraints
                    top.linkTo(cityText.bottom, paddings.extraSmall)
                    start.linkTo(parent.start, paddings.extraSmall)
                    end.linkTo(temperatureToday.start, paddings.extraSmall)
                }

                constrain(feelsLikeTemperature) {
                    top.linkTo(temperatureRange.bottom, paddings.extraSmall)
                    end.linkTo(parent.end, paddings.extraSmall)
                }

                constrain(iconImage) {
                    top.linkTo(weatherConditionText.bottom, paddings.extraSmall)
                    start.linkTo(parent.start, paddings.extraSmall)
                }
            }
        ) {
            Text(
                text = stringResource(R.string.celsius, temperature),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.layoutId(WeatherWidgetLayoutIDs.temperature)
            )

            Text(
                text = stringResource(R.string.your_location),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.layoutId(WeatherWidgetLayoutIDs.title)
            )

            Text(
                text = city,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.layoutId(WeatherWidgetLayoutIDs.city),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = stringResource(R.string.temperature_today),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.layoutId(WeatherWidgetLayoutIDs.temperatureToday)
            )

            Text(
                text = stringResource(R.string.temperature_range, minTemperature, maxTemperature),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.layoutId(WeatherWidgetLayoutIDs.temperatureRange)
            )

            Text(
                text = weatherCondition,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.layoutId(WeatherWidgetLayoutIDs.weatherCondition)
            )

            Text(
                text = stringResource(R.string.feelsLike, feelingTemperature),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.layoutId(WeatherWidgetLayoutIDs.feelingTemperature)
            )

            AsyncImage(
                model = "${BuildConfig.OPEN_WEATHER_IMG_URL}${icon}@2x.png",
                contentDescription = null,
                modifier = Modifier
                    .layoutId(WeatherWidgetLayoutIDs.icon)
                    .size(LifestyleHubTheme.sizes.weatherIconSize)
            )
        }
    }
}

@Composable
fun ErrorWeatherWidget(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.your_location_unavailable),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(
                    LifestyleHubTheme.paddings.medium
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

private object WeatherWidgetLayoutIDs {

    const val title = "title"
    const val city = "city"
    const val temperature = "temperature"
    const val temperatureToday = "temperature_today"
    const val temperatureRange = "temperature_range"
    const val feelingTemperature = "feeling_temperature"
    const val weatherCondition = "weather_condition"
    const val icon = "icon"
}

@Preview
@Composable
private fun WeatherWidgetPreview() {
    LifestyleHubTheme {
        WeatherWidget(
            temperature = 100,
            minTemperature = 1,
            maxTemperature = 11,
            feelingTemperature = 10,
            weatherCondition = "",
            city = "Хлевное",
            icon = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
        )
    }
}