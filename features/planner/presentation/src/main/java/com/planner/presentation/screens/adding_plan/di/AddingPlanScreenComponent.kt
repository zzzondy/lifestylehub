package com.planner.presentation.screens.adding_plan.di

import com.planner.presentation.screens.adding_plan.AddingPlanScreenViewModel
import dagger.Subcomponent

@AddingPlanScreenComponentScope
@Subcomponent(
    modules = [AddingPlanScreenModule::class]
)
interface AddingPlanScreenComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): AddingPlanScreenComponent
    }

    val addingPlanScreenViewModel: AddingPlanScreenViewModel
}