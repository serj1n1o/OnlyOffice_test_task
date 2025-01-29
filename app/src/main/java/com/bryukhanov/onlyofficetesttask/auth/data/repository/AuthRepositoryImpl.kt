package com.bryukhanov.onlyofficetesttask.auth.data.repository

import com.bryukhanov.onlyofficetesttask.auth.data.network.NetworkClient
import com.bryukhanov.onlyofficetesttask.auth.domain.api.AuthRepository
import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest

class AuthRepositoryImpl(private val networkClient: NetworkClient) : AuthRepository {

    override suspend fun authenticate(portalName: String, authRequest: AuthRequest): Int {
        return networkClient.doRequest(portalAddress = portalName, dto = authRequest).resultCode
    }

    override suspend fun logout(portalName: String): Int {
        return networkClient.doRequest(portalAddress = portalName, null).resultCode
    }
}