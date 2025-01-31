package com.bryukhanov.onlyofficetesttask.auth.data.network

import com.bryukhanov.onlyofficetesttask.auth.data.dto.AuthResponse
import com.bryukhanov.onlyofficetesttask.auth.data.dto.LogoutResponse
import com.bryukhanov.onlyofficetesttask.auth.data.dtoUser.UserResponse
import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthOfficeApi {

    @POST("/api/2.0/authentication")
    suspend fun authenticate(
        @Body request: AuthRequest,
    ): AuthResponse

    @POST("/api/2.0/authentication/logout")
    suspend fun logout(
        @Header("Host") host: String,
    ): LogoutResponse

    @GET("/api/2.0/people/@self")
    suspend fun getProfile(): UserResponse

}