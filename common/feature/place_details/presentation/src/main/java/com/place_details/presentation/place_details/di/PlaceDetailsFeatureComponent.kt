package com.place_details.presentation.place_details.di

import com.place_details.presentation.place_details.PlaceDetailsScreenViewModelFactoryFactory
import com.place_details.presentation.plan_adding.di.PlanAddingScreenComponent
import dagger.Subcomponent

@PlaceDetailsFeatureScope
@Subcomponent(
    modules = [PlaceDetailsFeatureModule::class]
)
interface PlaceDetailsFeatureComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): PlaceDetailsFeatureComponent
    }

    val placeDetailsScreenViewModelFactory: PlaceDetailsScreenViewModelFactoryFactory

    val planAddingScreenComponentFactory: PlanAddingScreenComponent.Factory
}