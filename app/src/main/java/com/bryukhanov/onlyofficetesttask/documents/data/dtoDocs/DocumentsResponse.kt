package com.bryukhanov.onlyofficetesttask.documents.data.dtoDocs

import com.bryukhanov.onlyofficetesttask.auth.data.dto.Response

data class DocumentsResponse(
    val count: Int,
    val response: Docs,
    val status: Int,
    val statusCode: Int,
) : Response()