package com.feature_main.presentation.di

import com.feature_main.presentation.screens.main.di.MainScreenComponent
import dagger.Subcomponent

@MainComponentScope
@Subcomponent(modules = [MainFeatureModule::class])
interface MainFeatureComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): MainFeatureComponent
    }

    val mainScreenComponentFactory: MainScreenComponent.Factory
}