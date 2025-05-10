package com.bryukhanov.onlyofficetesttask.auth.data.dtoUser

import com.bryukhanov.onlyofficetesttask.auth.data.dto.Response

data class UserResponse(
    val count: Int,
    val response: UserData,
    val status: Int,
    val statusCode: Int,
) : Response()