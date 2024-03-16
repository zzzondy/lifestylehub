package com.lifestylehub.di.application

import android.content.Context
import com.feature_main.presentation.di.MainFeatureComponent
import com.lifestylehub.di.navigation.NavigationComponent
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
}