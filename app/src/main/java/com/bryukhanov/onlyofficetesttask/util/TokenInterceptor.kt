package com.bryukhanov.onlyofficetesttask.util

import com.bryukhanov.onlyofficetesttask.auth.data.local.TokenStorage
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val tokenStorage: TokenStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val token = tokenStorage.getToken()

        return if (!token.isNullOrEmpty()) {
            val requestWithCookie = originRequest.newBuilder()
                .addHeader("Cookie", "asc_auth_key=$token")
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(requestWithCookie)
        } else {
            val requestAuth = originRequest.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(requestAuth)
        }
    }
}