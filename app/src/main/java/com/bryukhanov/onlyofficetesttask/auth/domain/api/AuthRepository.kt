package com.bryukhanov.onlyofficetesttask.auth.domain.api

import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest

interface AuthRepository {

    suspend fun authenticate(portalName: String, authRequest: AuthRequest): Int

    suspend fun logout(portalName: String): Int
}