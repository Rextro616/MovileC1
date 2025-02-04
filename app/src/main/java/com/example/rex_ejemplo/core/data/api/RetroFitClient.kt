package com.example.rex_ejemplo.core.data.api

import android.content.Context
import com.example.moviles_223251_proyecto.core.SharedPreference.TokenProvider
import com.example.moviles_223251_proyecto.core.data.api.interceptors.AuthInterceptor
import com.example.moviles_223251_proyecto.core.data.api.interceptors.ResponseInterceptor
import com.example.rex_ejemplo.core.domain.constants.ApiConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroFitClient {

    private lateinit var tokenProvider: TokenProvider

    fun init(ctx : Context){
        tokenProvider = TokenProvider(ctx)
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenProvider))
            .addInterceptor(ResponseInterceptor(tokenProvider))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }

    val getRetroFitClient: Retrofit get() = retrofit
}