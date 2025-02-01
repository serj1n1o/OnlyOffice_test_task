package com.bryukhanov.onlyofficetesttask.documents.ui.trash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryukhanov.onlyofficetesttask.documents.domain.api.DocumentsRepository
import com.bryukhanov.onlyofficetesttask.documents.domain.model.File
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Folder
import com.bryukhanov.onlyofficetesttask.documents.ui.DocsState
import com.bryukhanov.onlyofficetesttask.util.RequestResult
import kotlinx.coroutines.launch

class TrashViewModel(private val documentsRepository: DocumentsRepository) : ViewModel() {

    private val stateTrash = MutableLiveData<DocsState>()
    fun getStateDocs(): LiveData<DocsState> = stateTrash

    fun getTrash() {
        viewModelScope.launch {
            documentsRepository.getTrash().collect { result ->
                when (result) {
                    is RequestResult.Error -> {
                        stateTrash.postValue(DocsState.Error)
                    }

                    is RequestResult.Success -> {
                        result.data?.let { processResult(it.first, result.data.second) }
                    }
                }
            }
        }
    }

    private fun processResult(folders: List<Folder>, files: List<File>) {
        if (folders.isEmpty() && files.isEmpty()) {
            stateTrash.postValue(DocsState.Empty)
        } else {
            stateTrash.postValue(DocsState.Content(folders = folders, files = files))
        }

    }

}