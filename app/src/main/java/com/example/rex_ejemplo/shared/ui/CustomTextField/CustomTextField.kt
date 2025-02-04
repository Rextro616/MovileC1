package com.example.rex_ejemplo.shared.ui.CustomTextField

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomTextField(
    value : String,
    onValueChange : (String) -> Unit,
    label : @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = {onValueChange(it)},
        label = label,
        modifier = modifier
    )
}