package com.planner_feature_data.di

import android.content.Context
import com.planner_feature_data.local.database.PlannerFeatureDatabase
import com.planner_feature_data.local.repository.LocalPlannerFeatureRepository
import com.planner_feature_data.local.repository.LocalPlannerFeatureRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlannerFeatureDataModule {

    @Singleton
    @Provides
    fun providePlannerFeatureDatabase(context: Context): PlannerFeatureDatabase =
        PlannerFeatureDatabase.create(context)

    @Singleton
    @Provides
    fun provideLocalPlannerFeatureRepository(plannerFeatureDatabase: PlannerFeatureDatabase): LocalPlannerFeatureRepository =
        LocalPlannerFeatureRepositoryImpl(plannerFeatureDatabase)
}