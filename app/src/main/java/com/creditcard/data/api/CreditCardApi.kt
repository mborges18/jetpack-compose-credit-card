package com.creditcard.data.api

import com.creditcard.data.client.KtorClient
import com.creditcard.data.models.signin.SignInRequest
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