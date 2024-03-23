package com.place_details.presentation.place_details.di

import com.place_details.domain.use_cases.GetPlaceDetailsUseCase
import com.place_details.presentation.place_details.PlaceDetailsScreenViewModelFactoryFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [PlaceDetailsScreenUseCaseModule::class, RepositoryModule::class, NetworkModule::class]
)
class PlaceDetailsFeatureModule {

    @PlaceDetailsFeatureScope
    @Provides
    fun providePlaceDetailsScreenViewModelFactory(getPlaceDetailsUseCase: GetPlaceDetailsUseCase): PlaceDetailsScreenViewModelFactoryFactory =
        PlaceDetailsScreenViewModelFactoryFactory(getPlaceDetailsUseCase)
}