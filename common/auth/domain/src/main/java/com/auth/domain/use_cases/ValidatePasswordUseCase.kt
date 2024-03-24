package com.auth.domain.use_cases

class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationPasswordResult {
        if (password.length < 8) {
            return ValidationPasswordResult.VeryShortPassword
        }

        if (password.all { it.isLetter() } || password.all { it.isDigit() }) {
            return ValidationPasswordResult.NotContainsLettersOrDigits
        }

        if (password.filter { it.isLetter() }
                .all { it.isLowerCase() } ||
            password.filter { it.isLetter() }
                .all { it.isUpperCase() }
        ) {
            return ValidationPasswordResult.NotContainsLowerOrUpperCase
        }

        return ValidationPasswordResult.Success
    }
}

sealed class ValidationPasswordResult {
    data object Success : ValidationPasswordResult()

    data object VeryShortPassword : ValidationPasswordResult()

    data object NotContainsLettersOrDigits : ValidationPasswordResult()

    data object NotContainsLowerOrUpperCase : ValidationPasswordResult()
}