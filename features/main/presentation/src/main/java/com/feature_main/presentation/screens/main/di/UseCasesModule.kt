package com.feature_main.presentation.screens.main.di

import com.main.domain.repository.MainFeatureRepository
import com.main.domain.use_cases.GetLocationPermissionFlagUseCase
import com.main.domain.use_cases.GetPagedNearbyPlacesUseCase
import com.main.domain.use_cases.ObtainRandomTipUseCase
import com.main.domain.use_cases.ObtainUserLocationUseCase
import com.main.domain.use_cases.ObtainUserWeatherUseCase
import com.main.domain.use_cases.UpdateLocationPermissionFlagUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @MainScreenScope
    @Provides
    fun provideObtainUserWeatherUseCase(mainFeatureRepository: MainFeatureRepository): ObtainUserWeatherUseCase =
        ObtainUserWeatherUseCase(mainFeatureRepository)

    @MainScreenScope
    @Provides
    fun provideUpdateLocationPermissionFlagUseCase(mainFeatureRepository: MainFeatureRepository): UpdateLocationPermissionFlagUseCase =
        UpdateLocationPermissionFlagUseCase(mainFeatureRepository)

    @MainScreenScope
    @Provides
    fun provideGetLocationPermissionFlagUseCase(mainFeatureRepository: MainFeatureRepository): GetLocationPermissionFlagUseCase =
        GetLocationPermissionFlagUseCase(mainFeatureRepository)

    @MainScreenScope
    @Provides
    fun provideGetUserLocationUseCase(mainFeatureRepository: MainFeatureRepository): ObtainUserLocationUseCase =
        ObtainUserLocationUseCase(mainFeatureRepository)

    @MainScreenScope
    @Provides
    fun provideGetPagedNearbyPlacesUseCase(mainFeatureRepository: MainFeatureRepository): GetPagedNearbyPlacesUseCase =
        GetPagedNearbyPlacesUseCase(mainFeatureRepository)

    @MainScreenScope
    @Provides
    fun provideObtainRandomTipUseCase(mainFeatureRepository: MainFeatureRepository): ObtainRandomTipUseCase =
        ObtainRandomTipUseCase(mainFeatureRepository)
}