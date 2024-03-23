package com.planner.presentation.screens.adding_plan.di

import com.planner.domain.use_cases.AddPlanUseCase
import com.planner.presentation.screens.adding_plan.AddingPlanScreenViewModel
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCaseModule::class]
)
class AddingPlanScreenModule {

    @AddingPlanScreenComponentScope
    @Provides
    fun providePlansScreenViewModel(addPlanUseCase: AddPlanUseCase): AddingPlanScreenViewModel =
        AddingPlanScreenViewModel(addPlanUseCase)
}