package com.planner.presentation.di

import com.planner.data.repository.PlannerFeatureRepositoryImpl
import com.planner.domain.repository.PlannerFeatureRepository
import com.planner_feature_data.local.repository.LocalPlannerFeatureRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @PlannerFeatureComponentScope
    @Provides
    fun providePlannerFeatureRepository(localPlannerFeatureRepository: LocalPlannerFeatureRepository): PlannerFeatureRepository =
        PlannerFeatureRepositoryImpl(localPlannerFeatureRepository)
}