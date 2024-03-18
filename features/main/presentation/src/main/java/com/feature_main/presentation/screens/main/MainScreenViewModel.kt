package com.feature_main.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.common.ui.state_hoisting.StatefulViewModel
import com.common.ui.utils.UIText
import com.feature_main.presentation.R
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenAction
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenState
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenEffect
import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.use_cases.GetLocationPermissionFlagUseCase
import com.main.domain.use_cases.ObtainUserWeatherUseCase
import com.main.domain.use_cases.UpdateLocationPermissionFlagUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val obtainUserWeatherUseCase: ObtainUserWeatherUseCase,
    private val updateLocationPermissionFlagUseCase: UpdateLocationPermissionFlagUseCase,
    private val getLocationPermissionFlagUseCase: GetLocationPermissionFlagUseCase
) :
    StatefulViewModel<MainScreenState, MainScreenEffect, MainScreenAction>() {

    val state = _state.receiveAsFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, MainScreenState.Loading)

    private var isRationaleToShowLocationPermissionDialog = false

    init {
        obtainUserWeather()
    }

    override fun onAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.OnLocationPermissionGranted -> {
                obtainUserWeather()
            }

            is MainScreenAction.UpdateLocationPermissionFlag -> {
                updateLocationPermissionFlag(action.isRationaleShow)
            }
        }
    }

    private fun obtainUserWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState(MainScreenState.Loading)
            obtainUserWeatherUseCase().collectLatest { result ->
                when (result) {
                    is ObtainingUserWeatherResult.Success -> {
                        if (isRationaleToShowLocationPermissionDialog) {
                            updateLocationPermissionFlagUseCase(false)
                            isRationaleToShowLocationPermissionDialog = false
                        }

                        updateState(
                            MainScreenState.Content(
                                userWeather = result.weatherOnUserLocation,
                                isRationaleShowLocationPermissionDialog = isRationaleToShowLocationPermissionDialog,
                            )
                        )
                    }

                    is ObtainingUserWeatherResult.Error -> {

                    }

                    is ObtainingUserWeatherResult.LocationError -> {
                        isRationaleToShowLocationPermissionDialog =
                            getLocationPermissionFlagUseCase()
                        updateState(
                            MainScreenState.Content(
                                userWeather = null,
                                isRationaleShowLocationPermissionDialog = isRationaleToShowLocationPermissionDialog,
                                errorText = UIText.StringResource(R.string.your_location_unavailable),
                            )
                        )
                    }

                    is ObtainingUserWeatherResult.NetworkError -> {
                        updateState(MainScreenState.Content(
                            userWeather = null,
                            isRationaleShowLocationPermissionDialog = getLocationPermissionFlagUseCase(),
                            errorText = UIText.StringResource(R.string.network_error),
                        ))
                    }
                }
            }
        }
    }

    private fun updateLocationPermissionFlag(isRationaleShow: Boolean) {
        viewModelScope.launch {
            updateLocationPermissionFlagUseCase(isRationaleShow)
            isRationaleToShowLocationPermissionDialog = isRationaleShow
            updateState(
                MainScreenState.Content(
                    (state.value as MainScreenState.Content).userWeather,
                    isRationaleToShowLocationPermissionDialog,
                    if (isRationaleToShowLocationPermissionDialog) {
                        UIText.StringResource(R.string.your_location_unavailable)
                    } else {
                        null
                    }
                )
            )
        }
    }
}