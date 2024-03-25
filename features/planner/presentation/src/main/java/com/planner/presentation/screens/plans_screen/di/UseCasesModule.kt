package com.planner.presentation.screens.plans_screen.di

import com.planner.domain.repository.PlannerFeatureRepository
import com.planner.domain.use_cases.DeletePlanByIdUseCase
import com.planner.domain.use_cases.GetAllPlansUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @PlansScreenComponentScope
    @Provides
    fun provideGetAllPlansUseCase(plannerFeatureRepository: PlannerFeatureRepository): GetAllPlansUseCase =
        GetAllPlansUseCase(plannerFeatureRepository)

    @PlansScreenComponentScope
    @Provides
    fun provideDeletePlanByIdUseCase(plannerFeatureRepository: PlannerFeatureRepository): DeletePlanByIdUseCase =
        DeletePlanByIdUseCase(plannerFeatureRepository)
}