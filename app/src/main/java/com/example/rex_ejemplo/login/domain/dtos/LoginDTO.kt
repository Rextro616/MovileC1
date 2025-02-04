package com.example.rex_ejemplo.login.domain.dtos

data class LoginDTO(
    val username: String,
    val password: String
) {
    fun isValid(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}