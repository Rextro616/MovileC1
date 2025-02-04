package com.example.rex_ejemplo.home.data.api

import com.example.rex_ejemplo.core.domain.constants.ApiConfig
import com.example.rex_ejemplo.home.domain.adapters.ReceipeAdapter
import retrofit2.Response
import retrofit2.http.GET

interface GetReceipesApi {
    @GET(ApiConfig.API_RECIPES)
    suspend fun get_receipes() : Response<List<ReceipeAdapter>>
}