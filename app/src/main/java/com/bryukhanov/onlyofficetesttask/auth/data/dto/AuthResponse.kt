package com.bryukhanov.onlyofficetesttask.auth.data.dto

data class AuthResponse(
    val response: TokenData,
    val statusCode: Int,
) : Response()