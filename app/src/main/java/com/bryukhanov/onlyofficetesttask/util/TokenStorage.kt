package com.bryukhanov.onlyofficetesttask.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class TokenStorage(private val context: Context) {

    fun saveToken(token: String, portal: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("auth_token", token).apply()
        sharedPreferences.edit().putString("auth_portal", portal).apply()
    }

    fun getToken(): String? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("auth_token", null)
    }

    fun getPortal(): String? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("auth_portal", null)
    }

    fun clearData() {
        val sharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString("auth_token", null)
            putString("auth_portal", null)
        }
    }
}