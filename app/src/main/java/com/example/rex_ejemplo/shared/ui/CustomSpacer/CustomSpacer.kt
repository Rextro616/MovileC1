package com.example.rex_ejemplo.shared.ui.CustomSpacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(15.dp))
}