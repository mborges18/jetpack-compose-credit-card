package com.creditcard.features.authenticator.signin.domain.usecase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.creditcard.core.clientnetwork.HttpState
import com.creditcard.features.authenticator.signin.data.repository.CreditCardRepository
import com.creditcard.features.authenticator.signin.data.mapper.toRequest
import com.creditcard.features.authenticator.signin.domain.model.SignInModel
import com.creditcard.features.authenticator.signin.ui.SignInState
import com.creditcard.features.common.domain.usecase.ValidateEmailUseCase
import com.creditcard.features.common.domain.usecase.ValidatePasswordUseCase
import com.creditcard.features.common.ui.UiState

class SignInUseCaseImpl(
    private val repository: CreditCardRepository,
    private val emailUseCase: ValidateEmailUseCase,
    private val passwordUseCase: ValidatePasswordUseCase
): SignInUseCase {

    override suspend fun execute(model: SignInModel) : UiState<String> {
        return when (val response = repository.signIn(model.toRequest())) {
            is HttpState.Success -> {
                UiState.Success(response.data)
            }
            is HttpState.Unauthorized -> {
                UiState.Unauthorized
            }
            else -> {
                UiState.Error
            }
        }
    }
}

interface SignInUseCase {
    suspend fun execute(model: SignInModel): UiState<String>
}