package com.bryukhanov.onlyofficetesttask.di

import com.bryukhanov.onlyofficetesttask.documents.data.repository.DocumentsRepositoryImpl
import com.bryukhanov.onlyofficetesttask.documents.domain.api.DocumentsRepository
import com.bryukhanov.onlyofficetesttask.documents.ui.docs.DocsViewModel
import com.bryukhanov.onlyofficetesttask.documents.ui.rooms.RoomsViewModel
import com.bryukhanov.onlyofficetesttask.documents.ui.trash.TrashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val docModule = module {

    factory<DocumentsRepository> {
        DocumentsRepositoryImpl(networkClient = get())
    }

    viewModel {
        DocsViewModel(documentsRepository = get())
    }

    viewModel {
        TrashViewModel(documentsRepository = get())
    }

    viewModel {
        RoomsViewModel(documentsRepository = get())
    }

}