package com.place_details.presentation.place_details.di

import com.place_details.presentation.place_details.PlaceDetailsScreenViewModelFactoryFactory
import dagger.Subcomponent

@PlaceDetailsScreenScope
@Subcomponent(
    modules = [PlaceDetailsScreenModule::class]
)
interface PlaceDetailsScreenComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): PlaceDetailsScreenComponent
    }

    val placeDetailsScreenViewModelFactory: PlaceDetailsScreenViewModelFactoryFactory
}