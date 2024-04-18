package com.creditcard.ui.screens.authenticator.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creditcard.domain.models.authenticator.signin.SignInModel
import com.creditcard.domain.models.states.UiState
import com.creditcard.domain.usecases.authenticator.signin.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    var model: SignInModel
): ViewModel() {

    private val _uiState = MutableStateFlow(Any())
    val uiState: StateFlow<Any> = _uiState.asStateFlow()

    fun signIn() = viewModelScope.launch {
        _uiState.value = UiState.Loading(true)
        _uiState.value =  signInUseCase.execute(model)
    }
}