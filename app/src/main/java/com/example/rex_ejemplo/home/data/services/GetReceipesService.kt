package com.example.rex_ejemplo.home.data.services

import com.example.rex_ejemplo.core.data.api.RetroFitClient
import com.example.rex_ejemplo.home.data.api.GetReceipesApi
import com.example.rex_ejemplo.home.domain.adapters.ReceipeAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetReceipesService {
    private val getReceipesApi = RetroFitClient.createService(GetReceipesApi::class.java)

    suspend fun getReceipes() : Result<List<ReceipeAdapter>> {
        return withContext(Dispatchers.IO){
            try {
                val result = getReceipesApi.get_receipes()
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