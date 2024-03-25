package com.planner.presentation.di

import dagger.Module

@Module(
    includes = [RepositoryModule::class]
)
class PlannerFeatureModule