package com.bryukhanov.onlyofficetesttask.auth.data.dto

data class AuthResponse(
    val count: Int,
    val response: TokenData,
    val status: Int,
    val statusCode: Int,
) : Response()