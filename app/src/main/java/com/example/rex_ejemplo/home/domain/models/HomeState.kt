package com.example.rex_ejemplo.home.domain.models

import com.example.rex_ejemplo.home.domain.adapters.ReceipeAdapter

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    data class Success(val receipes : List<ReceipeAdapter>) : HomeState()
    data class Error(val message: String) : HomeState()
}