package com.example.rex_ejemplo.login.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviles_223251_proyecto.core.SharedPreference.UserPreference.UserPreferences
import com.example.rex_ejemplo.login.data.services.LoginUserService
import com.example.rex_ejemplo.login.domain.dtos.LoginDTO
import com.example.rex_ejemplo.login.domain.models.LoginState
import kotlinx.coroutines.launch

class LoginViewModel(app : Application) : AndroidViewModel(app){
    val username = mutableStateOf("")
    val password = mutableStateOf("")
    val loginState = mutableStateOf<LoginState>(LoginState.Idle)
    private val _loginService = LoginUserService()
    private val userPreferences = UserPreferences(app)

    fun login() {
        val loginDTO = getLoginUser()
        if (loginDTO.isValid()) {
            loginState.value = LoginState.Loading
            viewModelScope.launch {
                val result = _loginService.loginUser(loginDTO)
                result.fold(
                    onSuccess = {
                        userPreferences.saveToken(it.token)
                        Log.d("LOGIN_TAG", "login success $it")
                        loginState.value = LoginState.Success(it)
                    },
                    onFailure = {
                        loginState.value = LoginState.Error(it.message ?: "Error")
                        Log.d("LOGIN_TAG", "login error $it")

                    }
                )
            }
        }
    }

    private fun getLoginUser() : LoginDTO {
        return LoginDTO(
            username = username.value,
            password = password.value,
        )
    }

    fun restartState (){
        loginState.value = LoginState.Idle
    }
}