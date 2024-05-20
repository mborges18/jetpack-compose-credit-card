package com.creditcard.features.common.domain.usecase

import com.creditcard.core.validations.Validation.isInValidEmail
import com.creditcard.features.common.domain.model.ValidateResult

class ValidateEmailUseCaseImpl: ValidateEmailUseCase {

    override fun execute(email: String) : ValidateResult {
        if(email.isInValidEmail()) {
            return ValidateResult(
                hasError = true,
                message = "Email inválido"
            )
        }
        return ValidateResult(
            hasError = false,
        )
    }
}

interface ValidateEmailUseCase {
    fun execute(email: String) : ValidateResult
}
