package com.creditcard.features.authenticator.signin.data.repository

import com.creditcard.features.authenticator.signin.data.api.CreditCardApi
import com.creditcard.features.authenticator.signin.data.model.SignInRequest
import com.creditcard.core.clientnetwork.HttpState
import io.ktor.client.call.body

class CreditCardRepositoryImpl(
    private val api: CreditCardApi
): CreditCardRepository {

    override suspend fun signIn(body: SignInRequest): HttpState<String> {
        val response = api.signIn(body)
        return when(response.status.value) {
            200 -> HttpState.Success(response.body())
            401 -> HttpState.Unauthorized
            else -> HttpState.Error
        }
    }
}

interface CreditCardRepository {
    suspend fun signIn(body: SignInRequest): HttpState<String>
}