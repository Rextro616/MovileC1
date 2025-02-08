package com.example.rex_ejemplo.create_receipe.domain.dtos

data class CreateRecipeDTO(
    val name: String,
    val description: String,
    val imageUrl: String? = null // Ahora la imagen es opcional
) {
    fun isValid(): Boolean {
        return name.isNotEmpty() && description.isNotEmpty()
    }
}