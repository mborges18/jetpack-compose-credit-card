package com.creditcard.ui.screens.splash


import androidx.lifecycle.ViewModel
import com.creditcard.models.states.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SplashViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    suspend fun isUserLogged() {
        delay(5000)
        _uiState.value = UiState.Success
    }
}