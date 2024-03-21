package com.feature_main.presentation.di

import dagger.Module

@Module(
    includes = [RepositoryModule::class, NetworkModule::class],
)
class MainFeatureModule