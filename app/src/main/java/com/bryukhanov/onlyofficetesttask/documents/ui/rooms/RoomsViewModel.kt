package com.bryukhanov.onlyofficetesttask.documents.ui.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryukhanov.onlyofficetesttask.documents.domain.api.DocumentsRepository
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Room
import com.bryukhanov.onlyofficetesttask.util.RequestResult
import kotlinx.coroutines.launch

class RoomsViewModel(private val documentsRepository: DocumentsRepository) : ViewModel() {

    private val stateRooms = MutableLiveData<RoomsState>()
    fun getStateRooms(): LiveData<RoomsState> = stateRooms

    fun getRooms() {
        viewModelScope.launch {
            documentsRepository.getRooms().collect { result ->
                when (result) {
                    is RequestResult.Error -> {
                        stateRooms.postValue(RoomsState.Error)
                    }

                    is RequestResult.Success -> {
                        result.data?.let { processResult(it) }
                    }
                }
            }
        }
    }

    private fun processResult(rooms: List<Room>) {
        if (rooms.isEmpty()) {
            stateRooms.postValue(RoomsState.Empty)
        } else {
            stateRooms.postValue(RoomsState.Content(rooms))
        }

    }
}