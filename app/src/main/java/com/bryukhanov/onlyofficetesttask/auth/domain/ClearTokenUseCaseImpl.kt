package com.bryukhanov.onlyofficetesttask.auth.domain

import com.bryukhanov.onlyofficetesttask.auth.domain.api.ClearTokenUseCase
import com.bryukhanov.onlyofficetesttask.util.TokenStorage

class ClearTokenUseCaseImpl(private val tokenStorage: TokenStorage) : ClearTokenUseCase {

    override fun clearAuth() {
        tokenStorage.clearData()
    }

}