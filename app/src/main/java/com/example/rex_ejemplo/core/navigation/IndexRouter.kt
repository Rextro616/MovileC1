package com.example.rex_ejemplo.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.rex_ejemplo.core.domain.models.Routes
import com.example.rex_ejemplo.create_receipe.ui.viewmodels.CreateReceipeViewModel
import com.example.rex_ejemplo.create_receipe.ui.views.CreateRecipeView
import com.example.rex_ejemplo.home.ui.pages.HomePage
import com.example.rex_ejemplo.home.ui.viewmodels.HomeViewModel
import com.example.rex_ejemplo.login.ui.pages.LoginPage
import com.example.rex_ejemplo.login.ui.viewmodels.LoginViewModel
import com.example.rex_ejemplo.register.ui.pages.RegisterView
import com.example.rex_ejemplo.register.ui.viewmodels.RegisterViewModel

@Composable
fun IndexRouter(
    registerViewModel: RegisterViewModel,
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel,
    createReceipeViewModel: CreateReceipeViewModel
) {
    val route = remember {
        mutableStateOf(Routes.Login)
    }

    when(route.value){
        Routes.Login -> {
            LoginPage(
                homeViewModel = homeViewModel,
                loginViewModel = loginViewModel,
                onSuccess = {
                    route.value = Routes.Home
                },
                goToRegister = {
                    route.value = Routes.Register
                }
            )
        }
        Routes.Register -> RegisterView(
            registerViewModel,
            onSucess = {
                route.value = Routes.Login
            },
            goToLogin = {
                route.value = Routes.Login
            }
        )
        Routes.Home -> {
            HomePage(
                route,
                homeViewModel
            )
        }
        Routes.CreateReceipe -> {
            CreateRecipeView(
                route = route,
                createReceipeViewModel = createReceipeViewModel,
                homeViewModel = homeViewModel
            )
        }
    }
}