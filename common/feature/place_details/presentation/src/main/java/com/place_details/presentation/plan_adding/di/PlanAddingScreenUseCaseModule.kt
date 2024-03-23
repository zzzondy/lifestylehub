package com.place_details.presentation.plan_adding.di

import com.place_details.domain.repository.PlaceDetailsFeatureRepository
import com.place_details.domain.use_cases.AddNewPlanUseCase
import dagger.Module
import dagger.Provides

@Module
class PlanAddingScreenUseCaseModule {

    @PlanAddingScreenComponentScope
    @Provides
    fun provideAddNewPlanUseCase(placeDetailsFeatureRepository: PlaceDetailsFeatureRepository): AddNewPlanUseCase =
        AddNewPlanUseCase(placeDetailsFeatureRepository)
}