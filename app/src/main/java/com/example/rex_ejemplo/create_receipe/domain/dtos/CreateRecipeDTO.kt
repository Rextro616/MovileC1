package com.example.rex_ejemplo.create_receipe.domain.dtos

data class CreateRecipeDTO(val name : String, val description : String){
    fun isValid() : Boolean {
        return name.isNotEmpty() && description.isNotEmpty()
    }
}