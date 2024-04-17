package com.creditcard.ui.screens.authenticator.signin

import androidx.lifecycle.ViewModel
import com.creditcard.models.authenticator.signin.SignInModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(SignInModel())
    val uiState: StateFlow<SignInModel> = _uiState.asStateFlow()
}