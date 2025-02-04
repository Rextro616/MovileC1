package com.example.rex_ejemplo.create_receipe.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rex_ejemplo.create_receipe.data.services.CreateRecipeService
import com.example.rex_ejemplo.create_receipe.domain.dtos.CreateRecipeDTO
import com.example.rex_ejemplo.create_receipe.domain.models.CreateRecipeStatus
import kotlinx.coroutines.launch

class CreateReceipeViewModel(app : Application) : AndroidViewModel(app) {
    val name = mutableStateOf("")
    val description = mutableStateOf("")
    val recipeStatus = mutableStateOf<CreateRecipeStatus>(CreateRecipeStatus.Idle)
    private val _createRecipeService = CreateRecipeService()

    fun createRecipe(){
        val recipeDTO = getRecipe()
        if (recipeDTO.isValid()) {
            recipeStatus.value = CreateRecipeStatus.Loading
            viewModelScope.launch {
                val result = _createRecipeService.createRecipe(recipeDTO)
                result.fold(
                    onSuccess = {
                        recipeStatus.value = CreateRecipeStatus.Success(it)
                        Log.d("CREATE_RECEIPE", "createRecipe success: $it")
                    },
                    onFailure = {
                        recipeStatus.value = CreateRecipeStatus.Error(it.message ?: "Error")
                        Log.d("CREATE_RECEIPE", "createRecipe error: ${it}")
                    }
                )
            }
        }
    }

    private fun getRecipe() : CreateRecipeDTO{
        return CreateRecipeDTO(
            name = name.value,
            description = description.value
        )
    }
}