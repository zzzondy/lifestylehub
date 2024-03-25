package com.auth.presentation.screens.profile.di

import com.auth.presentation.screens.profile.ProfileScreenViewModel
import dagger.Subcomponent

@ProfileScreenComponentScope
@Subcomponent(
    modules = [ProfileScreenModule::class]
)
interface ProfileScreenComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): ProfileScreenComponent
    }

    val profileScreenViewModel: ProfileScreenViewModel
}