package com.bryukhanov.onlyofficetesttask.documents.ui

import com.bryukhanov.onlyofficetesttask.documents.domain.model.File
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Folder

sealed interface DocsState {

    data class Content(val folders: List<Folder>, val files: List<File>) : DocsState

    data object Error : DocsState

    data object Empty : DocsState
}