package com.example.rex_ejemplo.login.data.api

import com.example.rex_ejemplo.core.domain.constants.ApiConfig
import com.example.rex_ejemplo.login.domain.adapters.LoginResponseAdapter
import com.example.rex_ejemplo.login.domain.dtos.LoginDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginUserApi {
    @POST("${ApiConfig.API_USERS}login")
    suspend fun login(@Body loginRequest : LoginDTO) : Response<LoginResponseAdapter>
}