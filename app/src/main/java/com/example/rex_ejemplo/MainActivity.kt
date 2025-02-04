package com.example.rex_ejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.rex_ejemplo.core.data.api.RetroFitClient
import com.example.rex_ejemplo.core.navigation.IndexRouter
import com.example.rex_ejemplo.core.ui.theme.Rex_ejemploTheme
import com.example.rex_ejemplo.create_receipe.ui.viewmodels.CreateReceipeViewModel
import com.example.rex_ejemplo.home.ui.viewmodels.HomeViewModel
import com.example.rex_ejemplo.login.ui.viewmodels.LoginViewModel
import com.example.rex_ejemplo.register.ui.viewmodels.RegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        RetroFitClient.init(this)
        val registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val createReceipeViewModel = ViewModelProvider(this).get(CreateReceipeViewModel::class.java)
        setContent {
            Rex_ejemploTheme {
                IndexRouter(
                    registerViewModel = registerViewModel,
                    loginViewModel = loginViewModel,
                    homeViewModel = homeViewModel,
                    createReceipeViewModel = createReceipeViewModel
                )
            }
        }
    }
}
