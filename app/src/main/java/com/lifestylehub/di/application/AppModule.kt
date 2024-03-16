package com.lifestylehub.di.application

import com.common.network.NetworkModule
import com.feature_main.presentation.di.MainFeatureComponent
import com.lifestylehub.di.navigation.NavigationComponent
import dagger.Module

@Module(
    subcomponents = [NavigationComponent::class, MainFeatureComponent::class],
    includes = [NetworkModule::class]
)
class AppModule