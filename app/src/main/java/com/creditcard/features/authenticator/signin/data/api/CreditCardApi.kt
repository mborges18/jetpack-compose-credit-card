package com.creditcard.features.authenticator.signin.data.api

import com.creditcard.core.clientnetwork.KtorClient
import com.creditcard.features.authenticator.signin.data.model.SignInRequest
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse

private const val BASE_URL = "https://api-credit-card-792613245.development.catalystserverless.com/server"

class CreditCardApiImpl(
    private val client: KtorClient
): CreditCardApi {

    override suspend fun signIn(body: SignInRequest): HttpResponse {
        return client.httpRequest.post("$BASE_URL/signin")

    }
}

interface CreditCardApi {
    suspend fun signIn(body: SignInRequest): HttpResponse
}