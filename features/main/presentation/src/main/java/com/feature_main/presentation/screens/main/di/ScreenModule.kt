package com.feature_main.presentation.screens.main.di

import com.common.network.location.GetCurrentUserLocationUseCase
import com.feature_main.presentation.screens.main.MainScreenViewModel
import com.main.domain.use_cases.ObtainUserWeatherUseCase
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCasesModule::class]
)
class ScreenModule {

    @MainScreenScope
    @Provides
    fun provideMainScreenViewModel(obtainUserWeatherUseCase: ObtainUserWeatherUseCase): MainScreenViewModel =
        MainScreenViewModel(obtainUserWeatherUseCase)
}