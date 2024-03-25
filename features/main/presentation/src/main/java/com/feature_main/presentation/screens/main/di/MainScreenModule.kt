package com.feature_main.presentation.screens.main.di

import com.feature_main.presentation.screens.main.MainScreenViewModel
import com.main.domain.use_cases.GetLocationPermissionFlagUseCase
import com.main.domain.use_cases.GetPagedNearbyPlacesUseCase
import com.main.domain.use_cases.ObtainRandomTipUseCase
import com.main.domain.use_cases.ObtainUserLocationUseCase
import com.main.domain.use_cases.ObtainUserWeatherUseCase
import com.main.domain.use_cases.UpdateLocationPermissionFlagUseCase
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCasesModule::class]
)
class MainScreenModule {

        @MainScreenScope
        @Provides
        fun provideMainScreenViewModel(
            obtainUserWeatherUseCase: ObtainUserWeatherUseCase,
            updateLocationPermissionFlagUseCase: UpdateLocationPermissionFlagUseCase,
            getLocationPermissionFlagUseCase: GetLocationPermissionFlagUseCase,
            obtainUserLocationUseCase: ObtainUserLocationUseCase,
            getPagedNearbyPlacesUseCase: GetPagedNearbyPlacesUseCase,
            obtainRandomTipUseCase: ObtainRandomTipUseCase,
        ): MainScreenViewModel =
            MainScreenViewModel(
                obtainUserWeatherUseCase,
                updateLocationPermissionFlagUseCase,
                getLocationPermissionFlagUseCase,
                obtainUserLocationUseCase,
                getPagedNearbyPlacesUseCase,
                obtainRandomTipUseCase,
            )
}