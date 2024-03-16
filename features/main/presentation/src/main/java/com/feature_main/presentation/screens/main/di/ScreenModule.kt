package com.feature_main.presentation.screens.main.di

import com.feature_main.presentation.screens.main.MainScreenViewModel
import dagger.Module
import dagger.Provides

@Module
class ScreenModule {

    @MainScreenScope
    @Provides
    fun provideMainScreenViewModel(): MainScreenViewModel =
        MainScreenViewModel()
}