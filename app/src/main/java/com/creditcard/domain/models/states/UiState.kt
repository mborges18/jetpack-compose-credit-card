package com.creditcard.domain.models.states

sealed class UiState<out T> {
    data class Loading<Boolean>(val data: Boolean): UiState<Boolean>()
    data class Success<out T>(val data: T): UiState<T>()
    object Error: UiState<Nothing>()
    object Unauthorized: UiState<Nothing>()
    object Exists: UiState<Nothing>()
    object NotFound: UiState<Nothing>()
}
