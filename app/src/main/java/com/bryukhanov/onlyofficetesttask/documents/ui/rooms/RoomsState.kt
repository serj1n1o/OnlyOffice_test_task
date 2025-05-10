package com.bryukhanov.onlyofficetesttask.documents.ui.rooms

import com.bryukhanov.onlyofficetesttask.documents.domain.model.Room

sealed interface RoomsState {

    data class Content(val rooms: List<Room>) : RoomsState

    data object Error : RoomsState

    data object Empty : RoomsState
}