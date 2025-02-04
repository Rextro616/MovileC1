package com.example.rex_ejemplo.register.domain.dtos

data class RegisterDTO(
    val username : String,
    val password : String
) {
    fun isValid() : Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}