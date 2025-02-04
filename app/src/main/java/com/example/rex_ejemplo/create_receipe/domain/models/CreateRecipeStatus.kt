package com.example.rex_ejemplo.create_receipe.domain.models

import com.example.rex_ejemplo.create_receipe.domain.adapters.CreateRecipeAdapter

sealed class CreateRecipeStatus {
    object Idle : CreateRecipeStatus()
    object Loading : CreateRecipeStatus()
    data class Success(val response : CreateRecipeAdapter) : CreateRecipeStatus()
    data class Error(val message: String) : CreateRecipeStatus()
}