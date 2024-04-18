package com.creditcard.data.repository

import com.creditcard.data.api.CreditCardApi
import com.creditcard.data.models.signin.SignInRequest
import com.creditcard.data.models.state.HttpState
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