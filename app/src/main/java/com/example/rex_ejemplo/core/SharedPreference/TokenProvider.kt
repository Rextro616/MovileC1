package com.example.moviles_223251_proyecto.core.SharedPreference

import android.content.Context
import com.example.moviles_223251_proyecto.core.SharedPreference.UserPreference.UserPreferences
import kotlinx.coroutines.runBlocking

class TokenProvider(context: Context) {

    private val userPreferences = UserPreferences(context)

    fun getToken(): String? {
        return runBlocking {
            val token = userPreferences.getToken() ?: return@runBlocking null
            token ?: ""
        }
    }

    fun clearToken() {
        runBlocking {
            userPreferences.clearToken()
        }
    }
}