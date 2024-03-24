package com.lifestylehub.di.application

import com.auth.presentation.di.AuthFeatureComponent
import com.common.network.di.LocationModule
import com.common.network.di.NetworkModule
import com.feature_main.presentation.di.MainFeatureComponent
import com.lifestylehub.di.auth.AuthModule
import com.lifestylehub.di.local_storage.LocalStorageModule
import com.lifestylehub.di.navigation.NavigationComponent
import com.planner.presentation.di.PlannerFeatureComponent
import com.planner_feature_data.di.PlannerFeatureDataModule
import dagger.Module

@Module(
    subcomponents = [NavigationComponent::class, MainFeatureComponent::class, PlannerFeatureComponent::class, AuthFeatureComponent::class],
    includes = [NetworkModule::class, LocationModule::class, LocalStorageModule::class, PlannerFeatureDataModule::class, AuthModule::class]
)
class AppModule