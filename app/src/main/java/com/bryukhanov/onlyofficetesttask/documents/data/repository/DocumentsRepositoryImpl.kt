package com.bryukhanov.onlyofficetesttask.documents.data.repository

import com.bryukhanov.onlyofficetesttask.documents.data.dtoDocs.DocumentsResponse
import com.bryukhanov.onlyofficetesttask.documents.data.dtoDocs.FileDto
import com.bryukhanov.onlyofficetesttask.documents.data.dtoDocs.FolderDto
import com.bryukhanov.onlyofficetesttask.documents.data.dtoRoom.FolderInRooms
import com.bryukhanov.onlyofficetesttask.documents.data.dtoRoom.RoomResponse
import com.bryukhanov.onlyofficetesttask.documents.data.dtoTrash.TrashResponse
import com.bryukhanov.onlyofficetesttask.documents.domain.api.DocumentsRepository
import com.bryukhanov.onlyofficetesttask.documents.domain.model.File
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Folder
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Room
import com.bryukhanov.onlyofficetesttask.network.NetworkClient
import com.bryukhanov.onlyofficetesttask.util.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DocumentsRepositoryImpl(private val networkClient: NetworkClient) : DocumentsRepository {

    override fun getRooms(): Flow<RequestResult<List<Room>>> = flow {
        when (val result = networkClient.doRequestRooms()) {
            is RequestResult.Error -> {
                emit(RequestResult.Error())
            }

            is RequestResult.Success -> {
                val rooms = (result.data as RoomResponse).response.folders.map {
                    mapRoomDtoToRoom(it)
                }
                emit(RequestResult.Success(rooms))
            }
        }
    }

    override fun getTrash(): Flow<RequestResult<Pair<List<Folder>, List<File>>>> = flow {
        when (val result = networkClient.doRequestTrash()) {
            is RequestResult.Error -> {
                emit(RequestResult.Error())
            }

            is RequestResult.Success -> {
                val folders = (result.data as TrashResponse).response.folders.map {
                    mapFolderDtoToFolder(it)
                }
                val files = result.data.response.files.map {
                    mapFileDtoToFile(it)
                }
                emit(RequestResult.Success(Pair(folders, files)))
            }
        }
    }

    override fun getDocs(): Flow<RequestResult<Pair<List<Folder>, List<File>>>> = flow {
        when (val result = networkClient.doRequestDocs()) {
            is RequestResult.Error -> {
                emit(RequestResult.Error())
            }

            is RequestResult.Success -> {
                val folders = (result.data as DocumentsResponse).response.folders.map {
                    mapFolderDtoToFolder(it)
                }
                val files = result.data.response.files.map {
                    mapFileDtoToFile(it)
                }
                emit(RequestResult.Success(Pair(folders, files)))
            }
        }
    }

    override fun getFolderContent(folderId: Int): Flow<RequestResult<Pair<List<Folder>, List<File>>>> =
        flow {
            when (val result = networkClient.doRequestFolderContent(folderId)) {
                is RequestResult.Error -> {
                    emit(RequestResult.Error())
                }

                is RequestResult.Success -> {
                    val folders = (result.data as DocumentsResponse).response.folders.map {
                        mapFolderDtoToFolder(it)
                    }
                    val files = result.data.response.files.map {
                        mapFileDtoToFile(it)
                    }
                emit(RequestResult.Success(Pair(folders, files)))
            }
        }
    }

    private fun mapFolderDtoToFolder(folderDto: FolderDto): Folder {
        return Folder(
            title = folderDto.title,
            fileType = folderDto.fileEntryType,
            id = folderDto.id
        )
    }

    private fun mapFileDtoToFile(fileDto: FileDto): File {
        return File(
            title = fileDto.title,
            fileType = fileDto.fileEntryType
        )
    }

    private fun mapRoomDtoToRoom(folderInRooms: FolderInRooms): Room {
        return Room(
            title = folderInRooms.title,
            fileType = folderInRooms.fileEntryType
        )
    }
}