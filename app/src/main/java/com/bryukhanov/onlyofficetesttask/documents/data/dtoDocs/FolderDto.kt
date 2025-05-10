package com.bryukhanov.onlyofficetesttask.documents.data.dtoDocs

data class FolderDto(
    val access: Int,
    val canShare: Boolean,
    val created: String,
    val denyDownload: Boolean,
    val fileEntryType: Int,
    val filesCount: Int,
    val foldersCount: Int,
    val id: Int,
    val indexing: Boolean,
    val mute: Boolean,
    val new: Int,
    val parentId: Int,
    val pinned: Boolean,
    val rootFolderId: Int,
    val rootFolderType: Int,
    val shared: Boolean,
    val title: String,
    val updated: String,
)