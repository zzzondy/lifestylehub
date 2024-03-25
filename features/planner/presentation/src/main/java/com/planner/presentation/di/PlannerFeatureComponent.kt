package com.planner.presentation.di

import com.place_details.presentation.place_details.di.PlaceDetailsFeatureComponent
import com.planner.presentation.screens.adding_plan.di.AddingPlanScreenComponent
import com.planner.presentation.screens.plans_screen.di.PlansScreenComponent
import dagger.Subcomponent

@PlannerFeatureComponentScope
@Subcomponent(
    modules = [PlannerFeatureModule::class]
)
interface PlannerFeatureComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): PlannerFeatureComponent
    }

    val plansScreenComponentFactory: PlansScreenComponent.Factory

    val addingPlanScreenComponentFactory: AddingPlanScreenComponent.Factory

    val placeDetailsFeatureComponentFactory: PlaceDetailsFeatureComponent.Factory
}