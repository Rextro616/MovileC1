package com.example.rex_ejemplo.create_receipe.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rex_ejemplo.core.domain.models.Routes
import com.example.rex_ejemplo.core.ui.composables.BottomBar.BottomBar
import com.example.rex_ejemplo.core.ui.composables.TopBar.TopBar
import com.example.rex_ejemplo.core.ui.theme.OnPrimaryColor
import com.example.rex_ejemplo.core.ui.theme.PrimaryColor
import com.example.rex_ejemplo.core.ui.theme.TextColor
import com.example.rex_ejemplo.create_receipe.domain.models.CreateRecipeStatus
import com.example.rex_ejemplo.create_receipe.ui.viewmodels.CreateReceipeViewModel
import com.example.rex_ejemplo.home.ui.viewmodels.HomeViewModel
import com.example.rex_ejemplo.shared.ui.CustomSpacer.CustomSpacer
import com.example.rex_ejemplo.shared.ui.CustomText.CustomText
import com.example.rex_ejemplo.shared.ui.CustomTextField.CustomTextField

@Composable
fun CreateRecipeView(
    route : MutableState<String>,
    createReceipeViewModel: CreateReceipeViewModel,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val recipeStatus = createReceipeViewModel.recipeStatus.value
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(route)
        }
    ){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CustomText(
                text = "Crea una nueva receta",
                fontSize = 26.sp,
                color = TextColor,
                fontWeight = FontWeight.Bold
            )
            CustomSpacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                value = createReceipeViewModel.name.value,
                onValueChange = { createReceipeViewModel.name.value = it },
                label = { Text("Título", color = TextColor) },
                modifier = Modifier.fillMaxWidth()
            )
            CustomSpacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                value = createReceipeViewModel.description.value,
                onValueChange = { createReceipeViewModel.description.value = it },
                label = { Text("Descripción", color = TextColor) },
                modifier = Modifier.fillMaxWidth()
            )
            CustomSpacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { createReceipeViewModel.createRecipe() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Crear receta", color = OnPrimaryColor, fontSize = 16.sp)
            }
        }
        when(recipeStatus){
            is CreateRecipeStatus.Success -> {
                homeViewModel.getReceipes()
                createReceipeViewModel.name.value = "" // Limpiar el campo de título
                createReceipeViewModel.description.value = "" // Limpiar el campo de descripción
                createReceipeViewModel.recipeStatus.value = CreateRecipeStatus.Idle // Reiniciar el estado
                route.value = Routes.Home
            }

            is CreateRecipeStatus.Error -> {
                Text(
                    text = (recipeStatus).message,
                    modifier = Modifier.padding(it)
                )
            }
            CreateRecipeStatus.Idle -> {}
            CreateRecipeStatus.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(it)
                )
            }
        }
    }
}