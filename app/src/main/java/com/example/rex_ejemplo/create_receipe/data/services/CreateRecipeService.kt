package com.example.rex_ejemplo.create_receipe.data.services

import com.example.rex_ejemplo.core.data.api.RetroFitClient
import com.example.rex_ejemplo.create_receipe.data.api.CreateRecipeApi
import com.example.rex_ejemplo.create_receipe.domain.adapters.CreateRecipeAdapter
import com.example.rex_ejemplo.create_receipe.domain.dtos.CreateRecipeDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateRecipeService {
    private val _createRecipeApi = RetroFitClient.createService(CreateRecipeApi::class.java)

    suspend fun createRecipe(recipeRequest : CreateRecipeDTO) : Result<CreateRecipeAdapter> {
        return withContext(Dispatchers.IO){
            try {
                val result = _createRecipeApi.createRecipe(recipeRequest)
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