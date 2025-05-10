package com.bryukhanov.onlyofficetesttask.di

import com.bryukhanov.onlyofficetesttask.auth.data.repository.AuthRepositoryImpl
import com.bryukhanov.onlyofficetesttask.auth.domain.ClearTokenUseCaseImpl
import com.bryukhanov.onlyofficetesttask.auth.domain.api.AuthRepository
import com.bryukhanov.onlyofficetesttask.auth.domain.api.ClearTokenUseCase
import com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel.AuthViewModel
import com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel.ProfileViewModel
import com.bryukhanov.onlyofficetesttask.network.NetworkClient
import com.bryukhanov.onlyofficetesttask.network.OfficeNetworkClient
import com.bryukhanov.onlyofficetesttask.util.TokenStorage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel {
        AuthViewModel(authRepository = get(), clearTokenUseCase = get())
    }

    viewModel {
        ProfileViewModel(authRepository = get())
    }

    factory<ClearTokenUseCase> {
        ClearTokenUseCaseImpl(tokenStorage = get())
    }

    factory<AuthRepository> {
        AuthRepositoryImpl(networkClient = get(), tokenStorage = get())
    }

    factory<NetworkClient> {
        OfficeNetworkClient(get())
    }

    factory<ClearTokenUseCase> {
        ClearTokenUseCaseImpl(get())
    }

    factory {
        TokenStorage(get())
    }

}