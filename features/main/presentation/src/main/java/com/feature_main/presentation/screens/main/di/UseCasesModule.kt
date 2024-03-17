package com.feature_main.presentation.screens.main.di

import com.main.domain.repository.MainFeatureRepository
import com.main.domain.use_cases.ObtainUserWeatherUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @MainScreenScope
    @Provides
    fun provideObtainUserWeatherUseCase(mainFeatureRepository: MainFeatureRepository): ObtainUserWeatherUseCase =
        ObtainUserWeatherUseCase(mainFeatureRepository)
}