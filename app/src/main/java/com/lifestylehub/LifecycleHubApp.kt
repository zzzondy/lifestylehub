package com.lifestylehub

import android.app.Application
import android.content.Context
import com.auth.presentation.di.AuthFeatureComponent
import com.auth.presentation.di.AuthFeatureComponentProvider
import com.feature_main.presentation.di.MainFeatureComponent
import com.feature_main.presentation.di.MainFeatureComponentProvider
import com.lifestylehub.di.application.AppComponent
import com.lifestylehub.di.application.DaggerAppComponent
import com.planner.presentation.di.PlannerFeatureComponent
import com.planner.presentation.di.PlannerFeatureComponentProvider

class LifecycleHubApp : Application(), MainFeatureComponentProvider,
    PlannerFeatureComponentProvider, AuthFeatureComponentProvider {
    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent) {
            "AppComponent didn't initialize"
        }

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun provideMainFeatureComponent(): MainFeatureComponent =
        appComponent.mainFeatureComponentFactory.create()

    override fun providePlannerFeatureComponent(): PlannerFeatureComponent =
        appComponent.plannerFeatureComponentFactory.create()

    override fun provideAuthFeatureComponent(): AuthFeatureComponent =
        appComponent.authFeatureComponentFactory.create()
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is LifecycleHubApp -> appComponent
        else -> applicationContext.appComponent
    }