package com.example.rex_ejemplo.create_receipe.ui.viewmodels

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rex_ejemplo.core.services.CloudinaryService
import com.example.rex_ejemplo.create_receipe.data.services.CreateRecipeService
import com.example.rex_ejemplo.create_receipe.domain.dtos.CreateRecipeDTO
import com.example.rex_ejemplo.create_receipe.domain.models.CreateRecipeStatus
import kotlinx.coroutines.launch

class CreateReceipeViewModel(
    app: Application,
) : AndroidViewModel(app) {
    private val _createRecipeService = CreateRecipeService()
    val context = getApplication<Application>().applicationContext
    val name = mutableStateOf("")
    val description = mutableStateOf("")
    val recipeStatus = mutableStateOf<CreateRecipeStatus>(CreateRecipeStatus.Idle)
    val cloudinaryService = CloudinaryService(context)

    fun createRecipe(uri: Uri?) {
        viewModelScope.launch {
            recipeStatus.value = CreateRecipeStatus.Loading
            var imageUrl: String? = null

            if (uri != null) {
                imageUrl = cloudinaryService.uploadImageToCloudinary(uri)
                if (imageUrl == null) {
                    recipeStatus.value = CreateRecipeStatus.Error("Error al subir imagen")
                    return@launch
                }
            }

            // Creamos la receta con la URL de la imagen
            val recipeDTO = getRecipe(imageUrl)

            if (recipeDTO.isValid()) {
                val result = _createRecipeService.createRecipe(recipeDTO)
                result.fold(
                    onSuccess = {
                        recipeStatus.value = CreateRecipeStatus.Success(it)
                        Log.d("CREATE_RECEIPE", "Receta creada con éxito: $it")
                        resetFields() // Limpiar los campos tras la creación exitosa
                    },
                    onFailure = {
                        recipeStatus.value = CreateRecipeStatus.Error(it.message ?: "Error al crear receta")
                        Log.e("CREATE_RECEIPE", "Error al crear receta: $it")
                    }
                )
            }
        }
    }

    private fun getRecipe(imageUrl: String?): CreateRecipeDTO {
        return CreateRecipeDTO(
            name = name.value,
            description = description.value,
            imageUrl = imageUrl // Incluimos la URL de la imagen
        )
    }

    private fun resetFields() {
        name.value = ""
        description.value = ""
        recipeStatus.value = CreateRecipeStatus.Idle
    }
}