package com.bryukhanov.onlyofficetesttask.documents.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Folder(
    val title: String,
    val fileType: Int,
    val id: Int,
) : DocsItem, Parcelable
