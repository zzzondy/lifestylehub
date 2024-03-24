package com.auth.domain.results

sealed class CreatingUserResult {

    data object Success : CreatingUserResult()

    data object NetworkError : CreatingUserResult()

    data object Error : CreatingUserResult()
}