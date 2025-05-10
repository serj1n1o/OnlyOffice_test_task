package com.bryukhanov.onlyofficetesttask.documents.data.dtoTrash

import com.bryukhanov.onlyofficetesttask.auth.data.dto.Response

data class TrashResponse(
    val count: Int,
    val response: TrashDocs,
    val status: Int,
    val statusCode: Int,
) : Response()