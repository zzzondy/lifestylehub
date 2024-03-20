package com.feature_main.presentation.screens.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.common.ui.state_hoisting.StatefulViewModel
import com.common.ui.utils.UIText
import com.feature_main.presentation.R
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenAction
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenState
import com.feature_main.presentation.screens.main.state_hoisting.NearbySectionState
import com.feature_main.presentation.screens.main.state_hoisting.WeatherSectionState
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenEffect
import com.main.domain.models.UserLocation
import com.main.domain.models.results.ObtainingNearbyPlacesResult
import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.use_cases.GetLocationPermissionFlagUseCase
import com.main.domain.use_cases.GetPagedNearbyPlacesUseCase
import com.main.domain.use_cases.ObtainUserLocationUseCase
import com.main.domain.use_cases.ObtainUserWeatherUseCase
import com.main.domain.use_cases.UpdateLocationPermissionFlagUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val obtainUserWeatherUseCase: ObtainUserWeatherUseCase,
    private val updateLocationPermissionFlagUseCase: UpdateLocationPermissionFlagUseCase,
    private val getLocationPermissionFlagUseCase: GetLocationPermissionFlagUseCase,
    private val obtainUserLocationUseCase: ObtainUserLocationUseCase,
    private val getPagedNearbyPlacesUseCase: GetPagedNearbyPlacesUseCase,
) :
    StatefulViewModel<MainScreenState, MainScreenEffect, MainScreenAction>() {

    private var isRationaleToShowLocationPermissionDialog = false

    val state = _state.receiveAsFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            MainScreenState.Content(
                weatherSectionState = WeatherSectionState.Loading,
                nearbySectionState = NearbySectionState.Loading,
            )
        )

    private var userLocation: UserLocation? = null

    private var currentNearbyPlacesPagingOffset = 0

    private var currentWeatherSectionState: WeatherSectionState = WeatherSectionState.Loading
    private var currentNearbySectionState: NearbySectionState = NearbySectionState.Loading

    init {
        obtainAllInformation()
    }

    override fun onAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.OnLocationPermissionGranted -> {
                obtainAllInformation()
            }

            is MainScreenAction.UpdateLocationPermissionFlag -> {
                updateLocationPermissionFlag(action.isRationaleShow)
            }

            is MainScreenAction.LoadNextPageOfNearbyPlaces -> {
                onLoadNextPageOfNearbyPlaces()
            }

            is MainScreenAction.OnRefreshAllData -> {
                refreshAllData()
            }
        }
    }

    private fun refreshAllData() {
        currentWeatherSectionState = WeatherSectionState.Loading
        currentNearbySectionState = NearbySectionState.Loading
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            updateCurrentStateWithDataStates()
            obtainAllInformation()
        }
    }

    private fun obtainAllInformation() {
        viewModelScope.launch(Dispatchers.IO) {

            if (userLocation == null) {
                userLocation = obtainUserLocationUseCase()
            }

            if (state.value is MainScreenState.LocationUnavailable && userLocation != null) {
                val job = launch(
                    Dispatchers.IO
                ) {
                    currentWeatherSectionState = WeatherSectionState.Loading
                    currentNearbySectionState = NearbySectionState.Loading
                    updateState(
                        MainScreenState.Content(
                            weatherSectionState = currentWeatherSectionState,
                            nearbySectionState = currentNearbySectionState,
                        )
                    )
                }

                job.join()

                obtainUserWeather()
                obtainNearbyPlaces(currentNearbyPlacesPagingOffset, PAGING_LIMIT)
            } else if (userLocation != null) {
                obtainUserWeather()
                obtainNearbyPlaces(currentNearbyPlacesPagingOffset, PAGING_LIMIT)
            } else {
                isRationaleToShowLocationPermissionDialog =
                    getLocationPermissionFlagUseCase()
                updateState(
                    MainScreenState.LocationUnavailable(
                        isRationaleShowLocationPermissionDialog = isRationaleToShowLocationPermissionDialog
                    )
                )
            }
        }
    }

    private suspend fun obtainUserWeather() {
        currentWeatherSectionState = WeatherSectionState.Loading
        updateCurrentStateWithDataStates()

        when (val result =
            obtainUserWeatherUseCase(
                userLocation!!.latitude,
                userLocation!!.longitude
            )
        ) {
            is ObtainingUserWeatherResult.Success -> {
                if (isRationaleToShowLocationPermissionDialog) {
                    updateLocationPermissionFlagUseCase(false)
                    isRationaleToShowLocationPermissionDialog = false
                }

                currentWeatherSectionState = WeatherSectionState.Data(result.weatherOnUserLocation)
                updateCurrentStateWithDataStates()
            }

            is ObtainingUserWeatherResult.NetworkError -> {
                currentWeatherSectionState =
                    WeatherSectionState.Error(UIText.StringResource(R.string.network_error))
                updateCurrentStateWithDataStates()
            }
        }
    }

    private fun onLoadNextPageOfNearbyPlaces() {
        currentNearbyPlacesPagingOffset += PAGING_LIMIT
        currentNearbySectionState = NearbySectionState.Data(
            (currentNearbySectionState as NearbySectionState.Data).places,
            loadingOnAddingNewPlaces = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            updateCurrentStateWithDataStates()
            obtainNearbyPlaces(currentNearbyPlacesPagingOffset, PAGING_LIMIT)
        }
    }

    private suspend fun obtainNearbyPlaces(offset: Int, limit: Int) {
        if (currentNearbySectionState !is NearbySectionState.Data) {
            currentNearbySectionState = NearbySectionState.Loading
            updateCurrentStateWithDataStates()

            val result = getPagedNearbyPlacesUseCase(
                userLocation!!.latitude,
                userLocation!!.longitude,
                limit,
                offset
            )

            when (result) {
                is ObtainingNearbyPlacesResult.Success -> {
                    if (result.nearbyPlaces.isEmpty()) {
                        currentNearbySectionState = NearbySectionState.Empty
                        updateCurrentStateWithDataStates()
                    } else {
                        currentNearbySectionState = NearbySectionState.Data(
                            places = result.nearbyPlaces
                        )
                        updateCurrentStateWithDataStates()
                    }
                }

                is ObtainingNearbyPlacesResult.Error -> {
                    currentNearbySectionState = NearbySectionState.NetworkError(
                        UIText.StringResource(R.string.network_error)
                    )
                    updateCurrentStateWithDataStates()
                }
            }
        } else {
            val result = getPagedNearbyPlacesUseCase(
                userLocation!!.latitude,
                userLocation!!.longitude,
                limit,
                offset
            )
            when (result) {
                is ObtainingNearbyPlacesResult.Success -> {
                    if (result.nearbyPlaces.isNotEmpty()) {
                        currentNearbySectionState = NearbySectionState.Data(
                            places = (currentNearbySectionState as NearbySectionState.Data).places + result.nearbyPlaces
                        )
                        updateCurrentStateWithDataStates()
                    } else {
                        currentNearbySectionState = NearbySectionState.Data(
                            places = (currentNearbySectionState as NearbySectionState.Data).places,
                            dataLimitReached = true,
                        )
                        updateCurrentStateWithDataStates()
                    }

                }

                is ObtainingNearbyPlacesResult.Error -> {
                    currentNearbySectionState = NearbySectionState.Data(
                        places = ((state.value as MainScreenState.Content).nearbySectionState as NearbySectionState.Data).places,
                        errorOnAddingNewPlaces = UIText.StringResource(R.string.network_error)
                    )
                    updateCurrentStateWithDataStates()
                }
            }
        }
    }


    private fun updateLocationPermissionFlag(isRationaleShow: Boolean) {
        viewModelScope.launch {
            updateLocationPermissionFlagUseCase(isRationaleShow)
            isRationaleToShowLocationPermissionDialog = isRationaleShow
            updateState(
                MainScreenState.LocationUnavailable(
                    isRationaleShowLocationPermissionDialog = isRationaleToShowLocationPermissionDialog
                )
            )
        }
    }

    private suspend fun updateCurrentStateWithDataStates() {
        updateState(
            MainScreenState.Content(
                weatherSectionState = currentWeatherSectionState,
                nearbySectionState = currentNearbySectionState
            )
        )
    }

    companion object {
        private const val PAGING_LIMIT = 15
    }
}