package com.bryukhanov.onlyofficetesttask.auth.data.network

import com.bryukhanov.onlyofficetesttask.auth.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(portalAddress: String, dto: Any?): Response
}