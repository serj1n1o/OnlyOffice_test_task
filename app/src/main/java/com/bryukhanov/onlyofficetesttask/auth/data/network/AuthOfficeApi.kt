package com.bryukhanov.onlyofficetesttask.auth.data.network

import com.bryukhanov.onlyofficetesttask.auth.data.dto.AuthResponse
import com.bryukhanov.onlyofficetesttask.auth.data.dto.LogoutResponse
import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthOfficeApi {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("/api/2.0/authentication")
    suspend fun authenticate(
        @Header("Host") host: String,
        @Body request: AuthRequest,
    ): AuthResponse

    @Headers("Accept: application/json")
    @POST("/api/2.0/authentication/logout")
    suspend fun logout(
        @Header("Host") host: String,
    ): LogoutResponse

}