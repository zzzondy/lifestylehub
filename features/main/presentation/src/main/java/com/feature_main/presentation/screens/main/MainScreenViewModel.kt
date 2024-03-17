package com.feature_main.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.common.ui.state_hoisting.StatefulViewModel
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenAction
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenState
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenEffect
import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.use_cases.ObtainUserWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val obtainUserWeatherUseCase: ObtainUserWeatherUseCase,
) :
    StatefulViewModel<MainScreenState, MainScreenEffect, MainScreenAction>() {

    init {
        obtainUserWeather()
    }

    override fun onAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.OnLocationPermissionGranted -> {
                obtainUserWeather()
            }
        }
    }

    private fun obtainUserWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            obtainUserWeatherUseCase().collectLatest { result ->
                when (result) {
                    is ObtainingUserWeatherResult.Success -> {
                        updateState(MainScreenState.Content(result.weatherOnUserLocation))
                    }

                    is ObtainingUserWeatherResult.Error -> {

                    }

                    is ObtainingUserWeatherResult.LocationError -> {
                        updateState(MainScreenState.Content(null))
                    }

                    is ObtainingUserWeatherResult.NetworkError -> {

                    }
                }
            }
        }
    }
}