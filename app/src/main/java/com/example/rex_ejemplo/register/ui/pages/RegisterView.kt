package com.example.rex_ejemplo.register.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rex_ejemplo.core.ui.theme.BackgroundColor
import com.example.rex_ejemplo.core.ui.theme.OnPrimaryColor
import com.example.rex_ejemplo.core.ui.theme.PrimaryColor
import com.example.rex_ejemplo.core.ui.theme.SecondaryColor
import com.example.rex_ejemplo.core.ui.theme.TextColor
import com.example.rex_ejemplo.register.domain.models.RegisterState
import com.example.rex_ejemplo.register.ui.viewmodels.RegisterViewModel
import com.example.rex_ejemplo.shared.ui.CustomSpacer.CustomSpacer
import com.example.rex_ejemplo.shared.ui.CustomText.CustomText
import com.example.rex_ejemplo.shared.ui.CustomTextField.CustomTextField

@Composable
fun RegisterView(
    registerViewModel: RegisterViewModel,
    modifier: Modifier = Modifier,
    onSucess: () -> Unit,
    goToLogin : () -> Unit
) {
    val registerState = registerViewModel.registerState.value

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
    ){
        when(registerState){
            is RegisterState.Idle -> {
                CustomText(
                    text = "Regístrate",
                    fontSize = 30.sp,
                    color = TextColor,
                    fontWeight = FontWeight.Bold
                )
                CustomSpacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    value = registerViewModel.username.value,
                    onValueChange = { registerViewModel.username.value = it },
                    label = { Text("Usuario", color = TextColor) },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomSpacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    value = registerViewModel.password.value,
                    onValueChange = { registerViewModel.password.value = it },
                    label = { Text("Contraseña", color = TextColor) },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomSpacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { registerViewModel.register() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Registrar", color = OnPrimaryColor, fontSize = 16.sp)
                }

                CustomSpacer(modifier = Modifier.height(20.dp))

                CustomText(
                    text = "¿Ya tienes una cuenta? Inicia sesión",
                    fontSize = 14.sp,
                    color = SecondaryColor,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable(onClick = {
                        goToLogin()
                    })
                )
            }
            is RegisterState.Loading -> { CircularProgressIndicator()}
            is RegisterState.Error -> { Text(text = (registerState).message) }
            is RegisterState.Success -> { onSucess() }
        }
    }
}

