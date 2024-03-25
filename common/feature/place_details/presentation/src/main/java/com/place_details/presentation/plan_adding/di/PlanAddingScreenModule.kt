package com.place_details.presentation.plan_adding.di

import com.place_details.domain.use_cases.AddNewPlanUseCase
import com.place_details.presentation.plan_adding.PlanAddingScreenViewModelFactoryFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [PlanAddingScreenUseCaseModule::class]
)
class PlanAddingScreenModule {

    @PlanAddingScreenComponentScope
    @Provides
    fun providePlanAddingScreenViewModelFactory(addNewPlanUseCase: AddNewPlanUseCase): PlanAddingScreenViewModelFactoryFactory =
        PlanAddingScreenViewModelFactoryFactory(addNewPlanUseCase)
}