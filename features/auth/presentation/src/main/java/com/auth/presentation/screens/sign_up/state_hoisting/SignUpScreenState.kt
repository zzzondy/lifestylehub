package com.auth.presentation.screens.sign_up.state_hoisting

import com.common.ui.utils.UIText

data class SignUpScreenState(
    val password: String,
    val passwordSupportingText: UIText? = null,
    val isPasswordFieldError: Boolean = false,
    val errorMessage: UIText? = null,
)