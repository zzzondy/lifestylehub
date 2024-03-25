package com.feature_main.presentation.screens.main.components.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.common.ui.extensions.animateScrollToUp
import com.common.ui.theme.LifestyleHubTheme
import com.feature_main.presentation.R
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenAction
import com.feature_main.presentation.screens.main.state_hoisting.NearbySectionState
import com.feature_main.presentation.screens.main.state_hoisting.RandomTipSectionState
import com.feature_main.presentation.screens.main.state_hoisting.WeatherSectionState
import com.main.domain.models.places.PagingItem
import kotlinx.coroutines.launch

@Composable
fun MainScreenContentState(
    weatherSectionState: WeatherSectionState,
    nearbySectionState: NearbySectionState,
    randomTipSectionState: RandomTipSectionState,
    modifier: Modifier = Modifier,
    onAction: (MainScreenAction) -> Unit = {},
) {
    val listState = rememberLazyListState()
    val visibleItemIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                when (weatherSectionState) {
                    is WeatherSectionState.Data -> {
                        WeatherWidget(
                            temperature = weatherSectionState.userWeather.temperature.toInt(),
                            minTemperature = weatherSectionState.userWeather.minTemperature.toInt(),
                            maxTemperature = weatherSectionState.userWeather.maxTemperature.toInt(),
                            feelingTemperature = weatherSectionState.userWeather.temperatureFeelsLike.toInt(),
                            weatherCondition = weatherSectionState.userWeather.weatherCondition,
                            city = weatherSectionState.userWeather.currentCity,
                            icon = weatherSectionState.userWeather.icon,
                            modifier = Modifier
                                .height(LifestyleHubTheme.sizes.weatherCardHeight)
                                .fillMaxWidth()
                                .padding(LifestyleHubTheme.paddings.medium)
                        )
                    }

                    is WeatherSectionState.Loading -> {
                        LoadingWeatherWidget(
                            modifier = Modifier
                                .height(LifestyleHubTheme.sizes.weatherCardHeight)
                                .fillMaxWidth()
                                .padding(LifestyleHubTheme.paddings.medium)
                                .testTag(WeatherWidgetTestingTags.LOADING_WEATHER_WIDGET)
                        )
                    }

                    is WeatherSectionState.Error -> {
                        ErrorWeatherWidget(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(LifestyleHubTheme.sizes.weatherCardHeight)
                                .padding(LifestyleHubTheme.paddings.medium),
                            title = weatherSectionState.message.asString(),
                            onClick = {
                                onAction(MainScreenAction.OnRefreshAllData)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
            }

            item {
                when (randomTipSectionState) {
                    is RandomTipSectionState.Data -> {
                        RandomTipWidget(
                            randomTip = randomTipSectionState.tip,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = LifestyleHubTheme.paddings.medium)
                        )
                    }

                    is RandomTipSectionState.Loading -> {
                        LoadingRandomTipWidget(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = LifestyleHubTheme.paddings.medium)
                        )
                    }

                    is RandomTipSectionState.Error -> {
                        ErrorTipWidget(
                            message = randomTipSectionState.message.asString(), modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = LifestyleHubTheme.paddings.medium)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
            }

            item {
                Text(
                    text = stringResource(R.string.recommended_places),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium),
                )
            }


            when (nearbySectionState) {
                is NearbySectionState.Data -> {
                    items(nearbySectionState.places.size) { index ->
                        if (index + PAGING_THRESHOLD >= nearbySectionState.places.lastIndex &&
                            nearbySectionState.dataLimitReached.not() &&
                            nearbySectionState.errorOnAddingNewPlaces == null
                        ) {
                            onAction(MainScreenAction.LoadNextPageOfNearbyPlaces)
                        }

                        when (val pagingItem = nearbySectionState.places[index]) {
                            is PagingItem.Place -> {
                                PlaceWidget(
                                    place = pagingItem,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(LifestyleHubTheme.sizes.placeCardHeight)
                                        .padding(LifestyleHubTheme.paddings.medium),
                                    onClick = {
                                        onAction(
                                            MainScreenAction.OnPlaceClicked(pagingItem.id)
                                        )
                                    }
                                )
                            }
                        }
                    }

                    item {
                        if (nearbySectionState.loadingOnAddingNewPlaces) {
                            LoadingAppendWidget(
                                modifier = Modifier
                                    .padding(LifestyleHubTheme.paddings.medium)
                                    .fillMaxWidth()
                            )
                        } else if (nearbySectionState.errorOnAddingNewPlaces != null) {
                            ErrorWhenAppendWidget(
                                title = nearbySectionState.errorOnAddingNewPlaces.asString(),
                                modifier = Modifier
                                    .padding(LifestyleHubTheme.paddings.medium)
                                    .fillMaxWidth(),
                                onRefresh = {
                                    onAction(MainScreenAction.LoadNextPageOfNearbyPlaces)
                                }
                            )
                        } else if (nearbySectionState.dataLimitReached) {
                            Text(
                                text = stringResource(R.string.you_have_reached_maximum),
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(LifestyleHubTheme.paddings.medium)
                                    .fillMaxWidth(),
                            )
                        }
                    }
                }

                is NearbySectionState.Loading -> {
                    items(NEARBY_PLACES_PLACEHOLDERS_COUNT) {
                        LoadingPlaceWidget(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(LifestyleHubTheme.sizes.placeCardHeight)
                                .padding(LifestyleHubTheme.paddings.medium)
                        )
                    }
                }

                is NearbySectionState.NetworkError -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(LifestyleHubTheme.paddings.medium),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = nearbySectionState.message.asString(),
                                style = MaterialTheme.typography.headlineMedium,
                                textAlign = TextAlign.Center,
                            )

                            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))

                            Button(
                                onClick = {
                                    onAction(MainScreenAction.OnRefreshAllData)
                                }
                            ) {
                                Text(text = stringResource(R.string.try_again))
                            }
                        }
                    }
                }

                is NearbySectionState.Empty -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(LifestyleHubTheme.paddings.medium),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = stringResource(R.string.no_places_on_your_location),
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = visibleItemIndex >= BUTTON_TO_UP_TRIGGER,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(LifestyleHubTheme.paddings.medium),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToUp()
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowUp,
                    contentDescription = stringResource(
                        R.string.arrow_up
                    )
                )
            }
        }
    }

}

private const val NEARBY_PLACES_PLACEHOLDERS_COUNT = 4
private const val PAGING_THRESHOLD = 5
private const val BUTTON_TO_UP_TRIGGER = 4

object WeatherWidgetTestingTags {
    const val LOADING_WEATHER_WIDGET = "loading_weather"
    const val city = "city"
    const val temperature = "temperature"
    const val temperatureRange = "temperature_range"
    const val feelingTemperature = "feeling_temperature"
    const val weatherCondition = "weather_condition"
    const val icon = "icon"
}