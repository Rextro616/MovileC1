package com.example.rex_ejemplo.register.domain.models

import com.example.rex_ejemplo.register.domain.adapters.RegisterResponseAdapter

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val response : RegisterResponseAdapter) : RegisterState()
    data class Error(val message: String) : RegisterState()
}