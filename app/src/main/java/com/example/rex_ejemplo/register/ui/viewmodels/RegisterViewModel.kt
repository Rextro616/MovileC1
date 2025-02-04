package com.example.rex_ejemplo.register.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rex_ejemplo.register.data.services.RegisterUserService
import com.example.rex_ejemplo.register.domain.dtos.RegisterDTO
import com.example.rex_ejemplo.register.domain.models.RegisterState
import kotlinx.coroutines.launch

class RegisterViewModel(app : Application) : AndroidViewModel(app) {
    val password = mutableStateOf("")
    val username = mutableStateOf("")
    val registerState = mutableStateOf<RegisterState>(RegisterState.Idle)
    private val _registerService = RegisterUserService()

    fun register() {
        val registerDTO = getRegisterUser()
        if (registerDTO.isValid()) {
            registerState.value = RegisterState.Loading
            viewModelScope.launch {
                val result = _registerService.registerUser(registerDTO)
                result.fold(
                    onSuccess = {
                        registerState.value = RegisterState.Success(it)
                        Log.d("REGISTER_TAG", "register success $it")
                    },
                    onFailure = {
                        registerState.value = RegisterState.Error(it.message ?: "Error")
                        Log.d("REGISTER_TAG", "register error $it")

                    }
                )
            }
        }
    }

    private fun getRegisterUser() : RegisterDTO {
        return RegisterDTO(
            password = password.value,
            username = username.value
        )
    }
}