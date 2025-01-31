package com.bryukhanov.onlyofficetesttask.auth.domain

import com.bryukhanov.onlyofficetesttask.auth.data.local.TokenStorage
import com.bryukhanov.onlyofficetesttask.auth.domain.api.ClearTokenUseCase

class ClearTokenUseCaseImpl(private val tokenStorage: TokenStorage) : ClearTokenUseCase {

    override fun clearAuth() {
        tokenStorage.clearData()
    }

}