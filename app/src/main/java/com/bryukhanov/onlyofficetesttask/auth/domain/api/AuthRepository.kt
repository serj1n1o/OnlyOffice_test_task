package com.bryukhanov.onlyofficetesttask.auth.domain.api

import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest
import com.bryukhanov.onlyofficetesttask.auth.domain.model.User

interface AuthRepository {

    suspend fun authenticate(portalName: String, authRequest: AuthRequest): Int

    suspend fun logout(portalName: String): Int

    suspend fun getUserData(): User?
}