package com.bryukhanov.onlyofficetesttask.documents.data.dtoRoom

import com.bryukhanov.onlyofficetesttask.auth.data.dto.Response

data class RoomResponse(
    val count: Int,
    val response: Rooms,
    val status: Int,
    val statusCode: Int,
) : Response()