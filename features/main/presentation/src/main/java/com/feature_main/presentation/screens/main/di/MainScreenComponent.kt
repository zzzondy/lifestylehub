package com.feature_main.presentation.screens.main.di

import com.feature_main.presentation.screens.main.MainScreenViewModel
import dagger.Subcomponent

@MainScreenScope
@Subcomponent(
    modules = [MainScreenModule::class]
)
interface MainScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainScreenComponent
    }

    val mainScreenViewModel: MainScreenViewModel
}