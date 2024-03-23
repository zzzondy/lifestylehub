package com.planner.presentation.di

import com.common.navigation.BottomBarItem
import com.common.navigation.FeatureNavigationApi
import com.common.navigation.di.NavigationScope
import com.planner.presentation.navigation.PlannerFeatureBottomBarItem
import com.planner.presentation.navigation.PlannerFeatureNavigationApi
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class PlannerFeatureNavigationModule {

    @NavigationScope
    @Provides
    @IntoSet
    fun providePlannerFeatureNavigationApi(): FeatureNavigationApi =
        PlannerFeatureNavigationApi()

    @NavigationScope
    @Provides
    @IntoSet
    fun providePlannerFeatureBottomBarItem(): BottomBarItem =
        PlannerFeatureBottomBarItem()
}