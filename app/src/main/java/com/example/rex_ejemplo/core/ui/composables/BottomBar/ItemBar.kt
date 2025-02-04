package com.example.rex_ejemplo.core.ui.composables.BottomBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rex_ejemplo.shared.ui.CustomText.CustomText

@Composable
fun ItemBar(
    img : ImageVector,
    contentDescription : String,
    text : String,
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 12.dp)
            .clickable {  onClick() }
    ) {
        Image(
            imageVector = img,
            contentDescription = contentDescription,
            modifier = modifier
                .padding(bottom = 8.dp)
                .size(24.dp),
            colorFilter =  ColorFilter.tint(MaterialTheme.colorScheme.surface)
        )
        CustomText(
            text,
            fontSize = 12.sp,
        )
    }
}