package com.lifestylehub.features.main.screens.main.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.lifestylehub.R
import com.lifestylehub.common.ui.theme.LifestyleHubTheme

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
                val yourLocationText = createRefFor("yourLocation")

                constrain(yourLocationText) {
                    top.linkTo(parent.top, margin = paddings.extraSmall)
                    start.linkTo(parent.start, margin = paddings.extraSmall)
                }
            }
        ) {
            Text(
                text = stringResource(R.string.your_location),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
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
            city = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
        )
    }
}