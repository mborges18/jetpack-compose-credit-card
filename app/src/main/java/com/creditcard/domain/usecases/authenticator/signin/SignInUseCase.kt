package com.creditcard.domain.usecases.authenticator.signin

import androidx.core.text.isDigitsOnly
import com.creditcard.data.models.mapper.toRequest
import com.creditcard.data.models.state.HttpState
import com.creditcard.data.repository.CreditCardRepository
import com.creditcard.domain.models.authenticator.signin.SignInModel
import com.creditcard.domain.models.authenticator.signin.SignInState
import com.creditcard.domain.models.states.SignInUseCaseState
import com.creditcard.domain.models.validation.ValidationModel.isInValidEmail

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
             is HttpState.Unauthorized -> SignInUseCaseState.Invalid(SignInState(
                 emailIsInvalid = true,
                 emailMessage = "O e-email pode está errado",
                 passwordIsInvalid = true,
                 passwordMessage = "a senha pode está errada"
             ))
            else -> SignInUseCaseState.Error
        }
    }
}

interface SignInUseCase {
    suspend fun execute(model: SignInModel): SignInUseCaseState
}