package com.feature_main.presentation.screens.main.di

import com.feature_main.presentation.screens.main.MainScreenViewModel
import com.main.domain.use_cases.GetLocationPermissionFlagUseCase
import com.main.domain.use_cases.ObtainUserWeatherUseCase
import com.main.domain.use_cases.UpdateLocationPermissionFlagUseCase
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCasesModule::class]
)
class ScreenModule {

    @MainScreenScope
    @Provides
    fun provideMainScreenViewModel(
        obtainUserWeatherUseCase: ObtainUserWeatherUseCase,
        updateLocationPermissionFlagUseCase: UpdateLocationPermissionFlagUseCase,
        getLocationPermissionFlagUseCase: GetLocationPermissionFlagUseCase
    ): MainScreenViewModel =
        MainScreenViewModel(
            obtainUserWeatherUseCase,
            updateLocationPermissionFlagUseCase,
            getLocationPermissionFlagUseCase
        )
}