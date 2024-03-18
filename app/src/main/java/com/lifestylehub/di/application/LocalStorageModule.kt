package com.lifestylehub.di.application

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PERMISSIONS_PREFERENCES, Context.MODE_PRIVATE)
}

private const val PERMISSIONS_PREFERENCES = "PERMISSIONS_PREFERENCES"