package com.auth.presentation.screens.profile.state_hoisting

import com.auth.domain.models.User

sealed class ProfileScreenState {

    data class Content(val user: User) : ProfileScreenState()

    data object EmptyUser : ProfileScreenState()
}