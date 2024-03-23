package com.place_details.presentation.place_details.di

import com.place_details.domain.repository.PlaceDetailsFeatureRepository
import com.place_details.domain.use_cases.GetPlaceDetailsUseCase
import dagger.Module
import dagger.Provides

@Module
class PlaceDetailsScreenUseCaseModule {

    @PlaceDetailsScreenScope
    @Provides
    fun provideGetPlaceDetailsUseCase(placeDetailsFeatureRepository: PlaceDetailsFeatureRepository): GetPlaceDetailsUseCase =
        GetPlaceDetailsUseCase(placeDetailsFeatureRepository)
}