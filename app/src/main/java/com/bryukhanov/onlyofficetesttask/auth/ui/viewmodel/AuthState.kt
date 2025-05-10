package com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel

sealed interface AuthState {

    data class ErrorAuth(val errorCode: Int) : AuthState

    data object AuthSuccess : AuthState

    data object Default : AuthState
}