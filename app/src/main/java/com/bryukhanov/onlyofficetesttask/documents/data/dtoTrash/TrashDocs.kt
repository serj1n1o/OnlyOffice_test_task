package com.bryukhanov.onlyofficetesttask.documents.data.dtoTrash

import com.bryukhanov.onlyofficetesttask.documents.data.dtoDocs.FileDto
import com.bryukhanov.onlyofficetesttask.documents.data.dtoDocs.FolderDto

data class TrashDocs(
    val count: Int,
    val files: List<FileDto>,
    val folders: List<FolderDto>,
    val total: Int,
)