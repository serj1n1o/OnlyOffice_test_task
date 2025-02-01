package com.bryukhanov.onlyofficetesttask.network

import com.bryukhanov.onlyofficetesttask.auth.data.dto.Response
import com.bryukhanov.onlyofficetesttask.util.RequestResult

interface NetworkClient {

    suspend fun doRequestAuth(portalAddress: String, dto: Any?): Response

    suspend fun doRequestUser(portalAddress: String?): Response

    suspend fun doRequestDocs(): RequestResult<Response>

    suspend fun doRequestRooms(): RequestResult<Response>

    suspend fun doRequestTrash(): RequestResult<Response>
}