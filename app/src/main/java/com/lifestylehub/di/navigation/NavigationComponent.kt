package com.lifestylehub.di.navigation

import com.common.navigation.BottomBarItem
import com.common.navigation.FeatureNavigationApi
import com.common.navigation.di.NavigationScope
import com.feature_main.presentation.di.MainFeatureNavigationModule
import dagger.Subcomponent

@NavigationScope
@Subcomponent(
    modules = [MainFeatureNavigationModule::class]
)
interface NavigationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NavigationComponent
    }

    val bottomBarItems: Set<@JvmSuppressWildcards BottomBarItem>

    val featureNavigationApis: Set<@JvmSuppressWildcards FeatureNavigationApi>
}
