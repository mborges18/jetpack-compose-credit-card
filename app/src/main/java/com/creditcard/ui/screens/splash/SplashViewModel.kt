package com.creditcard.ui.screens.splash


import androidx.lifecycle.ViewModel
import com.creditcard.domain.models.states.SignInUseCaseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SplashViewModel: ViewModel() {

    private val _signInUseCaseState = MutableStateFlow<Any>(SignInUseCaseState.Loading(true))
    val uiState: StateFlow<Any> = _signInUseCaseState.asStateFlow()

    suspend fun isUserLogged() {
        delay(5000)
        _signInUseCaseState.value = SignInUseCaseState.Success("")
    }
}