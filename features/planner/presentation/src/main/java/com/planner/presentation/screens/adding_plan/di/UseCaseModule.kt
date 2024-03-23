package com.planner.presentation.screens.adding_plan.di

import com.planner.domain.repository.PlannerFeatureRepository
import com.planner.domain.use_cases.AddPlanUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @AddingPlanScreenComponentScope
    @Provides
    fun provideAddPlanUseCaseModule(plannerFeatureRepository: PlannerFeatureRepository): AddPlanUseCase =
        AddPlanUseCase(plannerFeatureRepository)
}