package com.creditcard.models.states

sealed class UiState {
    object Loading: UiState()
    object Success: UiState()
    object Error: UiState()
}
