package com.example.rex_ejemplo.login.domain.models

import com.example.rex_ejemplo.login.domain.adapters.LoginResponseAdapter

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val response : LoginResponseAdapter) : LoginState()
    data class Error(val message: String) : LoginState()
}
