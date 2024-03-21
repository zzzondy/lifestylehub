package com.feature_main.presentation.screens.place_details.di

import com.main.domain.repository.MainFeatureRepository
import com.main.domain.use_cases.GetPlaceDetailsUseCase
import dagger.Module
import dagger.Provides

@Module
class PlaceDetailsScreenUseCaseModule {

    @PlaceDetailsScreenScope
    @Provides
    fun provideGetPlaceDetailsUseCase(mainFeatureRepository: MainFeatureRepository): GetPlaceDetailsUseCase =
        GetPlaceDetailsUseCase(mainFeatureRepository)
}