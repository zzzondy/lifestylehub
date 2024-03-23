package com.lifestylehub.di.local_storage

import android.content.Context
import android.content.SharedPreferences
import com.main.data.local.database.PlaceDetailsDatabase
import com.planner_feature_data.local.database.PlannerFeatureDatabase
import com.planner_feature_data.local.repository.LocalPlannerFeatureRepository
import com.planner_feature_data.local.repository.LocalPlannerFeatureRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PERMISSIONS_PREFERENCES, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): PlaceDetailsDatabase =
        PlaceDetailsDatabase.create(context)
}

private const val PERMISSIONS_PREFERENCES = "PERMISSIONS_PREFERENCES"
