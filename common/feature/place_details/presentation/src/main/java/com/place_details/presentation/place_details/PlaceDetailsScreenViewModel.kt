package com.place_details.presentation.place_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.common.ui.state_hoisting.StatefulViewModel
import com.common.ui.utils.UIText
import com.place_details.domain.models.results.ObtainingPlaceDetailsResult
import com.place_details.domain.use_cases.GetPlaceDetailsUseCase
import com.place_details.presentation.R
import com.place_details.presentation.place_details.state_hoisting.PlaceDetailsScreenAction
import com.place_details.presentation.place_details.state_hoisting.PlaceDetailsScreenEffect
import com.place_details.presentation.place_details.state_hoisting.PlaceDetailsScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlaceDetailsScreenViewModel(
    private val placeId: String,
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
) :
    StatefulViewModel<PlaceDetailsScreenState, PlaceDetailsScreenEffect, PlaceDetailsScreenAction>() {

    val state = _state.receiveAsFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, PlaceDetailsScreenState.Loading)

    init {
        getPlaceDetails()
    }

    override fun onAction(action: PlaceDetailsScreenAction) {
        when (action) {
            is PlaceDetailsScreenAction.OnTryAgainClicked -> {
                getPlaceDetails()
            }

            is PlaceDetailsScreenAction.OnBackButtonClicked -> {
                onBackButtonClicked()
            }

            is PlaceDetailsScreenAction.OnAddPlanButtonClicked -> {
                onAddPlanButtonClicked()
            }
        }
    }

    private fun onAddPlanButtonClicked() {
        viewModelScope.launch {
            updateEffect(
                PlaceDetailsScreenEffect.NavigateToAddPlanScreen(
                    placeId = (state.value as PlaceDetailsScreenState.Content).placeDetails.id,
                    placeName = (state.value as PlaceDetailsScreenState.Content).placeDetails.name
                )
            )
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            updateEffect(PlaceDetailsScreenEffect.NavigateBack)
        }
    }

    private fun getPlaceDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState(PlaceDetailsScreenState.Loading)
            when (val result = getPlaceDetailsUseCase(placeId)) {
                is ObtainingPlaceDetailsResult.Success -> {
                    updateState(
                        PlaceDetailsScreenState.Content(
                            placeDetails = result.placeDetails,
                        )
                    )
                }

                is ObtainingPlaceDetailsResult.Error -> {
                    updateState(
                        PlaceDetailsScreenState.Error(
                            UIText.StringResource(R.string.network_error)
                        )
                    )
                }
            }
        }
    }

    class Factory(
        private val placeId: String,
        private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == PlaceDetailsScreenViewModel::class.java)
            return PlaceDetailsScreenViewModel(placeId, getPlaceDetailsUseCase) as T
        }

    }
}


class PlaceDetailsScreenViewModelFactoryFactory(
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
) {

    fun create(placeId: String): PlaceDetailsScreenViewModel.Factory {
        return PlaceDetailsScreenViewModel.Factory(placeId, getPlaceDetailsUseCase)
    }
}

