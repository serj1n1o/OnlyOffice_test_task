package com.bryukhanov.onlyofficetesttask.auth.data.network

import com.bryukhanov.onlyofficetesttask.auth.data.dto.Response

interface NetworkClient {

    suspend fun doRequestAuth(portalAddress: String, dto: Any?): Response

    suspend fun doRequestUser(portalAddress: String?): Response
}