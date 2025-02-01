package com.bryukhanov.onlyofficetesttask.documents.domain.api

import com.bryukhanov.onlyofficetesttask.documents.domain.model.File
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Folder
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Room
import com.bryukhanov.onlyofficetesttask.util.RequestResult
import kotlinx.coroutines.flow.Flow

interface DocumentsRepository {

    fun getRooms(): Flow<RequestResult<List<Room>>>

    fun getTrash(): Flow<RequestResult<Pair<List<Folder>, List<File>>>>

    fun getDocs(): Flow<RequestResult<Pair<List<Folder>, List<File>>>>

    fun getFolderContent(folderId: Int): Flow<RequestResult<Pair<List<Folder>, List<File>>>>
}