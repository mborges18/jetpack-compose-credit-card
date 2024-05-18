package com.creditcard.features.authenticator.signin.domain.usecase

import androidx.core.text.isDigitsOnly
import com.creditcard.core.clientnetwork.HttpState
import com.creditcard.features.authenticator.signin.data.repository.CreditCardRepository
import com.creditcard.features.authenticator.signin.domain.model.SignInModel
import com.creditcard.features.authenticator.signin.domain.model.SignInState
import com.creditcard.core.validations.Validation.isInValidEmail
import com.creditcard.features.authenticator.signin.data.mapper.toRequest

class SignInUseCaseImpl(
    private val repository: CreditCardRepository
): SignInUseCase {

    override suspend fun execute(model: SignInModel) : SignInUseCaseState {
        val modelState = SignInState()
        if(model.email.isInValidEmail()){
            modelState.emailIsInvalid = true
            modelState.emailMessage = "Email inválido"
        }
        if (model.password.isDigitsOnly()) {
            modelState.passwordIsInvalid = true
            modelState.passwordMessage = "Senha inválida"
        }
        val hasError = listOf(
            modelState.emailIsInvalid,
            modelState.passwordIsInvalid
        ).any {
            it
        }
        if(hasError) {
            return SignInUseCaseState.Invalid(modelState)
        }
        return when(val response = repository.signIn(model.toRequest())) {
             is HttpState.Success -> SignInUseCaseState.Success(response.data)
             is HttpState.Unauthorized -> SignInUseCaseState.Invalid(
                 SignInState(
                 emailIsInvalid = true,
                 emailMessage = "O e-email pode estar errado",
                 passwordIsInvalid = true,
                 passwordMessage = "a senha pode estar errada"
             )
             )
            else -> SignInUseCaseState.Error
        }
    }
}

interface SignInUseCase {
    suspend fun execute(model: SignInModel): SignInUseCaseState
}