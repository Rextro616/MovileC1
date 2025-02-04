package com.example.moviles_223251_proyecto.core.data.api.interceptors

import com.example.moviles_223251_proyecto.core.SharedPreference.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor(private val tokenProvider: TokenProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code == 401 || response.code == 500 || response.code == 403) {
            tokenProvider.clearToken()
        }

        return response
    }
}