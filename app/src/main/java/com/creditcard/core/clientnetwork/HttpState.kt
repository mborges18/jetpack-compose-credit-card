package com.creditcard.core.clientnetwork

sealed class HttpState<out T> {
    data class Success<out T>(val data: T): HttpState<T>()
    object NotFound: HttpState<Nothing>()
    object Unauthorized: HttpState<Nothing>()
    object Exists: HttpState<Nothing>()
    object Error: HttpState<Nothing>()
    object Failure: HttpState<Nothing>()
}
