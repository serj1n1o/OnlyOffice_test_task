package com.bryukhanov.onlyofficetesttask.documents.data.dtoRoom

data class Rooms(
    val count: Int,
    val files: List<Any>,
    val folders: List<FolderInRooms>,
    val new: Int,
    val startIndex: Int,
    val total: Int,
)