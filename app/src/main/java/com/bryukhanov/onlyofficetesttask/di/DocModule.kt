package com.bryukhanov.onlyofficetesttask.di

import com.bryukhanov.onlyofficetesttask.documents.data.repository.DocumentsRepositoryImpl
import com.bryukhanov.onlyofficetesttask.documents.domain.api.DocumentsRepository
import org.koin.dsl.module

val docModule = module {

    factory<DocumentsRepository> {
        DocumentsRepositoryImpl(networkClient = get())
    }

}