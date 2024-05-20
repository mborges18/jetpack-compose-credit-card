package com.creditcard.features.common.ui

sealed class UiState<out T> {
    object Initial: UiState<Nothing>()
    data class Success<out T>(val data: T): UiState<T>()
    object Error: UiState<Nothing>()
    object Loading: UiState<Nothing>()
    object Unauthorized: UiState<Nothing>()
    data class Invalid<out T>(val data: T): UiState<T>()
}
