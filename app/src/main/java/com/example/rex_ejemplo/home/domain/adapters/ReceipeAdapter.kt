package com.example.rex_ejemplo.home.domain.adapters

import com.example.rex_ejemplo.core.domain.adapters.UserAdapter

data class ReceipeAdapter(
    val id : Int,
    val name : String,
    val description : String,
    val user : UserAdapter
)