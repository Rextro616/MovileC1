package com.example.rex_ejemplo.register.data.services

import com.example.rex_ejemplo.core.data.api.RetroFitClient
import com.example.rex_ejemplo.register.data.api.RegisterUserAPI
import com.example.rex_ejemplo.register.domain.adapters.RegisterResponseAdapter
import com.example.rex_ejemplo.register.domain.dtos.RegisterDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterUserService {
    private val registerUserAPI = RetroFitClient.createService(RegisterUserAPI::class.java)

    suspend fun registerUser(registerRequest : RegisterDTO) : Result<RegisterResponseAdapter> {
        return withContext(Dispatchers.IO){
            try {
                val result = registerUserAPI.register(registerRequest)
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