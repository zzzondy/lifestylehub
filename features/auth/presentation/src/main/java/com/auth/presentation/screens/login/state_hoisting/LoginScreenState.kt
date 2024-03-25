package com.auth.presentation.screens.login.state_hoisting

import com.common.ui.utils.UIText

data class LoginScreenState(
    val login: String,
    val password: String,
    val errorMessage: UIText? = null,
)