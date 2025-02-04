package com.example.rex_ejemplo.login.ui.pages

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rex_ejemplo.core.ui.theme.BackgroundColor
import com.example.rex_ejemplo.core.ui.theme.OnPrimaryColor
import com.example.rex_ejemplo.core.ui.theme.PrimaryColor
import com.example.rex_ejemplo.core.ui.theme.SecondaryColor
import com.example.rex_ejemplo.core.ui.theme.TextColor
import com.example.rex_ejemplo.home.ui.viewmodels.HomeViewModel
import com.example.rex_ejemplo.login.domain.models.LoginState
import com.example.rex_ejemplo.login.ui.viewmodels.LoginViewModel
import com.example.rex_ejemplo.shared.ui.CustomSpacer.CustomSpacer
import com.example.rex_ejemplo.shared.ui.CustomText.CustomText
import com.example.rex_ejemplo.shared.ui.CustomTextField.CustomTextField

@Composable
fun LoginPage(
    onSuccess : () -> Unit,
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    goToRegister: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
    ) {
        CustomText(
            text = "Inicia Sesión",
            fontSize = 30.sp,
            color = TextColor,
            fontWeight = FontWeight.Bold
        )
        CustomSpacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            value = loginViewModel.username.value,
            onValueChange = { loginViewModel.username.value = it },
            label = { Text("Usuario", color = TextColor) },
            modifier = Modifier.fillMaxWidth()
        )
        CustomSpacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            value = loginViewModel.password.value,
            onValueChange = { loginViewModel.password.value = it },
            label = { Text("Contraseña", color = TextColor) },
            modifier = Modifier.fillMaxWidth()
        )
        CustomSpacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { loginViewModel.login() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Iniciar Sesión", color = OnPrimaryColor, fontSize = 16.sp)
        }

        CustomSpacer(modifier = Modifier.height(20.dp))

        CustomText(
            text = "¿No tienes una cuenta?",
            fontSize = 12.sp,
            color = SecondaryColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClick = {
                goToRegister()
            })
        )
    }
    when(val loginState = loginViewModel.loginState.value){
        is LoginState.Idle -> {}
        is LoginState.Error -> Text(text = (loginState).message)
        is LoginState.Loading -> CircularProgressIndicator()
        is LoginState.Success -> {
            homeViewModel.getReceipes()
            loginViewModel.restartState()
            onSuccess()
        }
    }
}