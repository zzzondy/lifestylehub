package com.feature_main.presentation.di

import com.common.navigation.BottomBarItem
import com.common.navigation.FeatureNavigationApi
import com.common.navigation.di.NavigationScope
import com.feature_main.presentation.navigation.MainFeatureBottomBarItem
import com.feature_main.presentation.navigation.MainFeatureNavigationApi
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class MainFeatureNavigationModule {

    @NavigationScope
    @Provides
    @IntoSet
    fun provideMainFeatureNavigationApi(): FeatureNavigationApi =
        MainFeatureNavigationApi()

    @NavigationScope
    @Provides
    @IntoSet
    fun provideMainFeatureBottomBarItem(): BottomBarItem = MainFeatureBottomBarItem()
}