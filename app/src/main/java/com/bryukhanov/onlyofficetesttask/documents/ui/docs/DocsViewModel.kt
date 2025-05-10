package com.bryukhanov.onlyofficetesttask.documents.ui.docs

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

class DocsViewModel(private val documentsRepository: DocumentsRepository) : ViewModel() {

    private val stateDocs = MutableLiveData<DocsState>()
    fun getStateDocs(): LiveData<DocsState> = stateDocs

    private val stateFolderContent = MutableLiveData<DocsState>()
    fun getStateFolderContent(): LiveData<DocsState> = stateFolderContent

    fun getDocuments() {
        viewModelScope.launch {
            documentsRepository.getDocs().collect { result ->
                when (result) {
                    is RequestResult.Error -> stateDocs.postValue(DocsState.Error)
                    is RequestResult.Success -> result.data?.first?.let {
                        processResultDocs(
                            it,
                            result.data.second
                        )
                    }
                }
            }
        }
    }

    fun openFolderContent(folderId: Int) {
        viewModelScope.launch {
            documentsRepository.getFolderContent(folderId).collect { result ->
                when (result) {
                    is RequestResult.Error -> stateDocs.postValue(DocsState.Error)
                    is RequestResult.Success -> result.data?.first?.let {
                        processResultContent(
                            it,
                            result.data.second
                        )
                    }
                }
            }
        }
    }

    private fun processResultDocs(folders: List<Folder>, files: List<File>) {
        if (folders.isEmpty() && files.isEmpty()) {
            stateDocs.postValue(DocsState.Empty)
        } else {
            stateDocs.postValue(DocsState.Content(folders = folders, files = files))
        }
    }

    private fun processResultContent(folders: List<Folder>, files: List<File>) {
        if (folders.isEmpty() && files.isEmpty()) {
            stateFolderContent.postValue(DocsState.Empty)
        } else {
            stateFolderContent.postValue(DocsState.Content(folders = folders, files = files))
        }
    }

}