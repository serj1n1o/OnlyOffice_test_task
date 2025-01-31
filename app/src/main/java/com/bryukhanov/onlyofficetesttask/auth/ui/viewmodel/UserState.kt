package com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel

import com.bryukhanov.onlyofficetesttask.auth.domain.model.User

sealed interface UserState {

    data class Content(val user: User) : UserState

    data object Empty : UserState
}
