package com.feature_main.presentation.screens.main.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.common.ui.theme.LifestyleHubTheme
import com.feature_main.presentation.R

@Composable
fun WeatherWidget(
    temperature: Double,
    temperatureRange: String,
    feelingTemperature: Double,
    weatherCondition: String,
    city: String,
    modifier: Modifier = Modifier,
    icon: Int = 1,
    onClick: () -> Unit = {}
) {
    val paddings = LifestyleHubTheme.paddings
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = ConstraintSet {
                val yourLocationText = createRefFor(WeatherWidgetLayoutIDs.title)
                val temperatureText = createRefFor(WeatherWidgetLayoutIDs.temperature)
                val cityText = createRefFor(WeatherWidgetLayoutIDs.city)

                constrain(temperatureText) {
                    width = Dimension.wrapContent
                    top.linkTo(parent.top, paddings.extraSmall)
                    end.linkTo(parent.end, paddings.extraSmall)
                }

                constrain(yourLocationText) {
                    top.linkTo(parent.top, paddings.extraSmall)
                    start.linkTo(parent.start, paddings.extraSmall)
                    end.linkTo(temperatureText.start, paddings.extraSmall)
                }

                constrain(cityText) {
                    width = Dimension.fillToConstraints
                    top.linkTo(yourLocationText.bottom, paddings.extraSmall)
                    start.linkTo(parent.start, paddings.extraSmall)
                    end.linkTo(yourLocationText.end, paddings.extraSmall)
                }


            }
        ) {
            Text(
                text = stringResource(R.string.your_location),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.layoutId(WeatherWidgetLayoutIDs.title)
            )

            Text(
                text = city,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.layoutId(WeatherWidgetLayoutIDs.city),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private object WeatherWidgetLayoutIDs {

    const val title = "title"
    const val city = "city"
    const val temperature = "temperature"
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
            temperature = 1.0,
            temperatureRange = "",
            feelingTemperature = 1.0,
            weatherCondition = "",
            city = "fgsdfgsdfgsdfgsdgdsgsdfgsdfgsdfgsdfgdgdfgsdg",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
        )
    }
}