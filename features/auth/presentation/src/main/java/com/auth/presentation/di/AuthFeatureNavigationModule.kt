package com.auth.presentation.di

import com.auth.presentation.navigation.AuthFeatureBottomBarItem
import com.auth.presentation.navigation.AuthFeatureNavigationApi
import com.common.navigation.BottomBarItem
import com.common.navigation.FeatureNavigationApi
import com.common.navigation.di.NavigationScope
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class AuthFeatureNavigationModule {

    @NavigationScope
    @Provides
    @IntoSet
    fun provideAuthFeatureNavigationApi(): FeatureNavigationApi =
        AuthFeatureNavigationApi()

    @NavigationScope
    @Provides
    @IntoSet
    fun provideAuthFeatureBottomBarItem(): BottomBarItem = AuthFeatureBottomBarItem()
}