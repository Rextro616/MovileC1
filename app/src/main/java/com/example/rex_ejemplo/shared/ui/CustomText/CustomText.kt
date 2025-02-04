package com.example.rex_ejemplo.shared.ui.CustomText

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.White,
    fontSize: TextUnit = 16.sp
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight,
        modifier = modifier
    )
}