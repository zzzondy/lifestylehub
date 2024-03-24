package com.lifestylehub.di.application

import android.content.Context
import com.auth.presentation.di.AuthFeatureComponent
import com.feature_main.presentation.di.MainFeatureComponent
import com.lifestylehub.di.navigation.NavigationComponent
import com.planner.presentation.di.PlannerFeatureComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    val navigationComponentFactory: NavigationComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val mainFeatureComponentFactory: MainFeatureComponent.Factory

    val plannerFeatureComponentFactory: PlannerFeatureComponent.Factory

    val authFeatureComponentFactory: AuthFeatureComponent.Factory
}