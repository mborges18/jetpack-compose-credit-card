package com.creditcard.domain.usecases.authenticator.signin

import com.creditcard.data.models.mapper.toRequest
import com.creditcard.data.models.state.HttpState
import com.creditcard.data.repository.CreditCardRepository
import com.creditcard.domain.models.authenticator.signin.SignInModel
import com.creditcard.domain.models.states.UiState

class SignInUseCaseImpl(
    private val repository: CreditCardRepository
): SignInUseCase {

    override suspend fun execute(model: SignInModel) : UiState<String> {
        return when(val response = repository.signIn(model.toRequest())) {
             is HttpState.Success -> UiState.Success(response.data)
             is HttpState.Unauthorized -> UiState.Unauthorized
            else -> UiState.Error
        }
    }
}

interface SignInUseCase {
    suspend fun execute(model: SignInModel): UiState<String>
}