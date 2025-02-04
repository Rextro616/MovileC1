package com.example.rex_ejemplo.core.ui.composables.BottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rex_ejemplo.core.domain.models.Routes
import com.example.rex_ejemplo.core.ui.theme.PrimaryColor

@Composable
fun BottomBar(
    route : MutableState<String>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(PrimaryColor)
            .padding(10.dp)
            .height(70.dp)

    ){
        ItemBar(
            img = Icons.Filled.Home,
            contentDescription = "Item Home",
            text = "Home",
            onClick = {route.value = Routes.Home}
        )
        ItemBar(
            img = Icons.Filled.AddCircle,
            contentDescription = "Item Create",
            text = "Create receipe",
            onClick = {route.value = Routes.CreateReceipe}
        )
        ItemBar(
            img = Icons.Filled.ExitToApp,
            contentDescription = "Item Exit",
            text = "Logout",
            onClick = {route.value = Routes.Login}
        )
    }
}