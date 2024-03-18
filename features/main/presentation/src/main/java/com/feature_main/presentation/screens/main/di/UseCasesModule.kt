package com.feature_main.presentation.screens.main.di

import com.main.domain.repository.MainFeatureRepository
import com.main.domain.use_cases.GetLocationPermissionFlagUseCase
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
}