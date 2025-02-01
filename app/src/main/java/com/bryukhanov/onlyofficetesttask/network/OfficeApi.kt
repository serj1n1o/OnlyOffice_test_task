package com.bryukhanov.onlyofficetesttask.network

import com.bryukhanov.onlyofficetesttask.auth.data.dto.AuthResponse
import com.bryukhanov.onlyofficetesttask.auth.data.dto.LogoutResponse
import com.bryukhanov.onlyofficetesttask.auth.data.dtoUser.UserResponse
import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest
import com.bryukhanov.onlyofficetesttask.documents.data.dtoDocs.DocumentsResponse
import com.bryukhanov.onlyofficetesttask.documents.data.dtoRoom.RoomResponse
import com.bryukhanov.onlyofficetesttask.documents.data.dtoTrash.TrashResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface OfficeApi {

    @POST("/api/2.0/authentication")
    suspend fun authenticate(
        @Body request: AuthRequest,
    ): AuthResponse

    @POST("/api/2.0/authentication/logout")
    suspend fun logout(
        @Header("Host") host: String,
    ): LogoutResponse

    @GET("/api/2.0/people/@self")
    suspend fun getProfile(): UserResponse

    @GET("/api/2.0/files/@my")
    suspend fun getDocuments(): DocumentsResponse

    @GET("/api/2.0/files/rooms")
    suspend fun getRoom(): RoomResponse

    @GET("/api/2.0/files/@trash")
    suspend fun getTrash(): TrashResponse

}