package com.example.rex_ejemplo.home.ui.composables.recipeItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rex_ejemplo.core.ui.theme.PrimaryColor
import com.example.rex_ejemplo.home.domain.adapters.ReceipeAdapter
import com.example.rex_ejemplo.shared.ui.CustomText.CustomText

@Composable
fun RecipeItem(
    receipe : ReceipeAdapter,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(
                PrimaryColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ){
        CustomText(text = receipe.name)
        CustomText(text = receipe.description)
    }
}