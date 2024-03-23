package com.planner.presentation.screens.plans_screen.di

import com.planner.presentation.screens.plans_screen.PlansScreenViewModel
import dagger.Subcomponent

@PlansScreenComponentScope
@Subcomponent(
    modules = [PlansScreenModule::class]
)
interface PlansScreenComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): PlansScreenComponent
    }

    val plansScreenViewModel: PlansScreenViewModel
}