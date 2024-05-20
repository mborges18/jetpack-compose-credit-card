package com.creditcard.features.common.domain.usecase

import com.creditcard.features.common.domain.model.ValidateResult

class ValidatePasswordUseCaseImpl: ValidatePasswordUseCase {

    override fun execute(email: String) : ValidateResult {
        if(email.all { char -> char.isDigit() } || email.all { it.isLetter() }) {
            return ValidateResult(
                hasError = true,
                message = "Senha inválida"
            )
        }
        return ValidateResult(
            hasError = false,
        )
    }
}

interface ValidatePasswordUseCase {
    fun execute(email: String) : ValidateResult
}
