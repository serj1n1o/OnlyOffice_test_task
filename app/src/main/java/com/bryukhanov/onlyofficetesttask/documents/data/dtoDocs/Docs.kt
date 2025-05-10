package com.bryukhanov.onlyofficetesttask.documents.data.dtoDocs

data class Docs(
    val count: Int,
    val files: List<FileDto>,
    val folders: List<FolderDto>,
    val total: Int,
)