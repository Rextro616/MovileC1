package com.example.rex_ejemplo.login.data.services

import com.example.rex_ejemplo.core.data.api.RetroFitClient
import com.example.rex_ejemplo.login.data.api.LoginUserApi
import com.example.rex_ejemplo.login.domain.adapters.LoginResponseAdapter
import com.example.rex_ejemplo.login.domain.dtos.LoginDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUserService {
    private val loginApi = RetroFitClient.createService(LoginUserApi::class.java)

    suspend fun loginUser(loginRequest : LoginDTO) : Result<LoginResponseAdapter> {
        return withContext(Dispatchers.IO){
            try {
                val result = loginApi.login(loginRequest)
                if(result.isSuccessful){
                    val body = result.body()
                    if (body != null) {
                        Result.success(body)
                    } else {
                        Result.failure(Exception("Empty response body"))
                    }
                }else{
                    Result.failure(Exception(result.errorBody()?.string() ?: "Error"))
                }
            }catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}