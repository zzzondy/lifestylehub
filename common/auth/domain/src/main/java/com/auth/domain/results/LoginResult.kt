package com.auth.domain.results

sealed class LoginResult {

    data object Success : LoginResult()

    data object WrongLoginOrPassword : LoginResult()

    data object Error : LoginResult()
}