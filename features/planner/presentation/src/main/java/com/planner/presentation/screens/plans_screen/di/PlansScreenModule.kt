package com.planner.presentation.screens.plans_screen.di

import com.planner.domain.use_cases.DeletePlanByIdUseCase
import com.planner.domain.use_cases.GetAllPlansUseCase
import com.planner.presentation.screens.plans_screen.PlansScreenViewModel
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCasesModule::class]
)
class PlansScreenModule {

    @PlansScreenComponentScope
    @Provides
    fun providePlansScreenViewModel(
        getAllPlansUseCase: GetAllPlansUseCase,
        deletePlanByIdUseCase: DeletePlanByIdUseCase
    ): PlansScreenViewModel =
        PlansScreenViewModel(getAllPlansUseCase, deletePlanByIdUseCase)
}