package com.feature_main.presentation.screens.place_details.di

import com.feature_main.presentation.screens.place_details.PlaceDetailsScreenViewModelFactoryFactory
import com.main.domain.use_cases.GetPlaceDetailsUseCase
import dagger.Module
import dagger.Provides

@Module(
    includes = [PlaceDetailsScreenUseCaseModule::class]
)
class PlaceDetailsScreenModule {

    @PlaceDetailsScreenScope
    @Provides
    fun providePlaceDetailsScreenViewModelFactory(getPlaceDetailsUseCase: GetPlaceDetailsUseCase): PlaceDetailsScreenViewModelFactoryFactory =
        PlaceDetailsScreenViewModelFactoryFactory(getPlaceDetailsUseCase)
}