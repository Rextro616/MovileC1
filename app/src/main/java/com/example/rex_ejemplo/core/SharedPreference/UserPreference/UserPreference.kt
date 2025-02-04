package com.example.moviles_223251_proyecto.core.SharedPreference.UserPreference

import android.content.Context
import android.util.Log

class UserPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        Log.d("USER_PREFERENCES_LOG", "saveToken: $token")
        sharedPreferences.edit().putString("TOKEN", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("TOKEN").apply()
    }
}