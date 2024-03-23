package com.place_details.presentation.plan_adding.di

import com.place_details.presentation.plan_adding.PlanAddingScreenViewModelFactoryFactory
import dagger.Subcomponent

@PlanAddingScreenComponentScope
@Subcomponent(
    modules = [PlanAddingScreenModule::class]
)
interface PlanAddingScreenComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): PlanAddingScreenComponent
    }

    val planAddingScreenViewModelFactory: PlanAddingScreenViewModelFactoryFactory
}