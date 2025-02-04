package com.example.rex_ejemplo.create_receipe.domain.adapters

import com.example.rex_ejemplo.core.domain.adapters.UserAdapter

data class CreateRecipeAdapter(
    val id : Int,
    val name : String,
    val description : String,
    val user : UserAdapter
)