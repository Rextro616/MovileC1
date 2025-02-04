package com.example.rex_ejemplo.create_receipe.data.api

import com.example.rex_ejemplo.core.domain.constants.ApiConfig
import com.example.rex_ejemplo.create_receipe.domain.adapters.CreateRecipeAdapter
import com.example.rex_ejemplo.create_receipe.domain.dtos.CreateRecipeDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateRecipeApi {
    @POST(ApiConfig.API_RECIPES)
    suspend fun createRecipe(@Body recipeRequest : CreateRecipeDTO) : Response<CreateRecipeAdapter>
}