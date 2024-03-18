package com.feature_main.presentation.screens.main.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.common.ui.extensions.shimmerEffect
import com.common.ui.theme.LifestyleHubTheme

@Composable
fun MainScreenLoadingState(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        userScrollEnabled = false,
    ) {
        item {
            LoadingWeatherWidget(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LifestyleHubTheme.sizes.weatherCardHeight)
                    .padding(LifestyleHubTheme.paddings.medium)
            )
        }
    }
}

@Composable
private fun LoadingWeatherWidget(
    modifier: Modifier = Modifier
) {
    val paddings = LifestyleHubTheme.paddings

    ElevatedCard(
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = ConstraintSet {
                val yourLocationText = createRefFor(LoadingWeatherWidgetLayoutIDs.title)
                val temperatureText = createRefFor(LoadingWeatherWidgetLayoutIDs.temperature)
                val cityText = createRefFor(LoadingWeatherWidgetLayoutIDs.city)
                val temperatureRange = createRefFor(LoadingWeatherWidgetLayoutIDs.temperatureRange)
                val temperatureToday = createRefFor(LoadingWeatherWidgetLayoutIDs.temperatureToday)
                val weatherConditionText =
                    createRefFor(LoadingWeatherWidgetLayoutIDs.weatherCondition)
                val feelsLikeTemperature =
                    createRefFor(LoadingWeatherWidgetLayoutIDs.feelingTemperature)
                val iconImage = createRefFor(LoadingWeatherWidgetLayoutIDs.icon)

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
                text = "",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .layoutId(LoadingWeatherWidgetLayoutIDs.temperature)
                    .fillMaxWidth(0.2f)
                    .shimmerEffect(true, MaterialTheme.shapes.large)
            )

            Text(
                text = "",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .layoutId(LoadingWeatherWidgetLayoutIDs.title)
                    .shimmerEffect(true, MaterialTheme.shapes.medium)
            )

            Text(
                text = "",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .layoutId(LoadingWeatherWidgetLayoutIDs.city)
                    .shimmerEffect(true, MaterialTheme.shapes.medium),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = "",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .layoutId(LoadingWeatherWidgetLayoutIDs.temperatureToday)
                    .fillMaxWidth(0.4f)
                    .shimmerEffect(true, MaterialTheme.shapes.medium),
            )

            Text(
                text = "",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .layoutId(LoadingWeatherWidgetLayoutIDs.temperatureRange)
                    .fillMaxWidth(0.3f)
                    .shimmerEffect(true, MaterialTheme.shapes.medium),
            )

            Text(
                text = "",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .layoutId(LoadingWeatherWidgetLayoutIDs.weatherCondition)
                    .shimmerEffect(true, MaterialTheme.shapes.medium),
            )

            Text(
                text = "",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .layoutId(LoadingWeatherWidgetLayoutIDs.feelingTemperature)
                    .fillMaxWidth(0.3f)
                    .shimmerEffect(true, MaterialTheme.shapes.medium),
            )

            AsyncImage(
                model = "",
                contentDescription = null,
                modifier = Modifier
                    .layoutId(LoadingWeatherWidgetLayoutIDs.icon)
                    .size(LifestyleHubTheme.sizes.weatherIconSize)
                    .shimmerEffect(true, MaterialTheme.shapes.extraLarge),
            )
        }
    }
}

private object LoadingWeatherWidgetLayoutIDs {

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
private fun LoadingWeatherWidgetPreview() {
    LifestyleHubTheme {
        LoadingWeatherWidget(
            modifier = Modifier
                .fillMaxWidth()
                .height(LifestyleHubTheme.sizes.weatherCardHeight)
                .padding(LifestyleHubTheme.paddings.medium)
        )
    }
}