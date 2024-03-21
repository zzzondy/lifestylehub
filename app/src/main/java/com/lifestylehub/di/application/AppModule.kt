package com.lifestylehub.di.application

import com.common.network.di.LocationModule
import com.common.network.di.NetworkModule
import com.feature_main.presentation.di.MainFeatureComponent
import com.lifestylehub.di.local_storage.LocalStorageModule
import com.lifestylehub.di.navigation.NavigationComponent
import dagger.Module

@Module(
    subcomponents = [NavigationComponent::class, MainFeatureComponent::class],
    includes = [NetworkModule::class, LocationModule::class, LocalStorageModule::class]
)
class AppModule