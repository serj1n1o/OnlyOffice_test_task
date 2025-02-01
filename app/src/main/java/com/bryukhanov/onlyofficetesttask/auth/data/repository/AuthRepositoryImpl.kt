package com.bryukhanov.onlyofficetesttask.auth.data.repository

import com.bryukhanov.onlyofficetesttask.auth.data.dto.AuthResponse
import com.bryukhanov.onlyofficetesttask.auth.data.dtoUser.UserResponse
import com.bryukhanov.onlyofficetesttask.auth.domain.api.AuthRepository
import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest
import com.bryukhanov.onlyofficetesttask.auth.domain.model.StatusCode
import com.bryukhanov.onlyofficetesttask.auth.domain.model.User
import com.bryukhanov.onlyofficetesttask.network.NetworkClient
import com.bryukhanov.onlyofficetesttask.util.TokenStorage

class AuthRepositoryImpl(
    private val networkClient: NetworkClient,
    private val tokenStorage: TokenStorage,
) : AuthRepository {

    override suspend fun authenticate(portalName: String, authRequest: AuthRequest): Int {
        val result = networkClient.doRequestAuth(portalAddress = portalName, dto = authRequest)
        if (result.resultCode == StatusCode.CODE_OK) {
            val token = (result as AuthResponse).response.token
            tokenStorage.saveToken(token = token, portal = portalName)
        } else {
            tokenStorage.clearData()
        }
        return result.resultCode
    }

    override suspend fun logout(portalName: String): Int {
        tokenStorage.clearData()
        return networkClient.doRequestAuth(portalAddress = portalName, null).resultCode
    }

    override suspend fun getUserData(): User? {
        val portal = tokenStorage.getPortal()
        val response = networkClient.doRequestUser(portal)
        if (response.resultCode != StatusCode.CODE_OK) {
            return null
        } else {
            val userResponse = response as UserResponse
            return userResponseToUser(userResponse)
        }
    }

    private fun userResponseToUser(userResponse: UserResponse): User {
        val userFirstName = userResponse.response.firstName
        val userLastName = userResponse.response.lastName
        val email = userResponse.response.email
        val avatar = userResponse.response.avatar
        return User(
            userName = "$userFirstName $userLastName",
            email = email,
            avatar = avatar,
        )
    }
}