package com.example.rex_ejemplo.home.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rex_ejemplo.home.data.services.GetReceipesService
import com.example.rex_ejemplo.home.domain.models.HomeState
import kotlinx.coroutines.launch

class HomeViewModel(app : Application) : AndroidViewModel(app) {
    val homeState = mutableStateOf<HomeState>(HomeState.Idle)
    private val _getReceipesService = GetReceipesService()

    fun getReceipes() {
        homeState.value = HomeState.Loading
        viewModelScope.launch {
            val result = _getReceipesService.getReceipes()
            result.fold(
                onSuccess = {
                    homeState.value = HomeState.Success(it)
                    Log.d("HOME_LOG", "getReceipes success: $it")
                },
                onFailure = {
                    homeState.value = HomeState.Error(it.message ?: "Error")
                    Log.d("HOME_LOG", "getReceipes error: ${it}")
                }
            )
        }
    }
}