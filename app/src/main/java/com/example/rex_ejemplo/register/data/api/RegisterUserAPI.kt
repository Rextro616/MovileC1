package com.example.rex_ejemplo.register.data.api

import com.example.rex_ejemplo.core.domain.constants.ApiConfig
import com.example.rex_ejemplo.register.domain.adapters.RegisterResponseAdapter
import com.example.rex_ejemplo.register.domain.dtos.RegisterDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterUserAPI {
    @POST("${ApiConfig.API_USERS}register")
    suspend fun register(@Body registerRequest : RegisterDTO) : Response<RegisterResponseAdapter>
}