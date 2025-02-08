package com.example.rex_ejemplo.create_receipe.ui.views

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil3.compose.AsyncImage
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
import java.io.File

@Composable
fun CreateRecipeView(
    route : MutableState<String>,
    createReceipeViewModel: CreateReceipeViewModel,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val recipeStatus = createReceipeViewModel.recipeStatus.value
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Configurar el launcher para abrir la cámara
    val takePictureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (!success) {
            imageUri = null // Si la foto falla, no mostrar nada
        }
    }

    // Crear un archivo temporal para guardar la imagen
    fun createImageFile(): Uri {
        val file = File(context.filesDir, "recipe_photo_${System.currentTimeMillis()}.jpg")
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider", // Debe coincidir con `android:authorities` en AndroidManifest.xml
            file
        )
    }


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

            // Botón para Capturar Imagen
            Button(
                onClick = {
                    val uri = createImageFile()
                    imageUri = uri
                    takePictureLauncher.launch(uri)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Tomar Foto", color = OnPrimaryColor, fontSize = 16.sp)
            }

            CustomSpacer(modifier = Modifier.height(20.dp))

            // Mostrar la imagen capturada si existe
            imageUri?.let { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = "Imagen de receta",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                CustomSpacer(modifier = Modifier.height(10.dp))

                // Botón para eliminar la imagen y tomar otra
                Button(
                    onClick = { imageUri = null },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Eliminar Foto", color = OnPrimaryColor, fontSize = 14.sp)
                }
            }

            // Botón para Crear Receta
            CustomSpacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { createReceipeViewModel.createRecipe(imageUri) },
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