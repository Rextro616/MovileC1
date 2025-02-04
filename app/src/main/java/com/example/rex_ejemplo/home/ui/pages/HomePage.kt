package com.example.rex_ejemplo.home.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rex_ejemplo.core.ui.composables.BottomBar.BottomBar
import com.example.rex_ejemplo.core.ui.composables.TopBar.TopBar
import com.example.rex_ejemplo.core.ui.theme.BackgroundColor
import com.example.rex_ejemplo.core.ui.theme.PrimaryColor
import com.example.rex_ejemplo.core.ui.theme.SecondaryColor
import com.example.rex_ejemplo.core.ui.theme.TextColor
import com.example.rex_ejemplo.home.domain.models.HomeState
import com.example.rex_ejemplo.home.ui.composables.recipeItem.RecipeItem
import com.example.rex_ejemplo.home.ui.viewmodels.HomeViewModel
import com.example.rex_ejemplo.shared.ui.CustomText.CustomText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    route : MutableState<String>,
    homeViewModel : HomeViewModel,
    modifier: Modifier = Modifier
) {
    val homeState = homeViewModel.homeState.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomText(
                        text = "Recetas",
                        fontSize = 24.sp,
                        color = TextColor,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryColor
                )
            )
        },
        bottomBar =  {
            BottomBar(route)
        },
        containerColor = BackgroundColor
    ){
        LazyColumn(
            modifier = modifier.padding(it)
        ){
            when(homeState){
                is HomeState.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }
                is HomeState.Error -> {
                    item {
                        Text(text = (homeState).message)
                    }
                }
                is HomeState.Success -> {
                    val recipes = (homeState).receipes
                    items(recipes.size) {
                            recipeIdx ->
                        RecipeItem(
                            receipe = recipes[recipeIdx],
                        )
                    }
                }
                HomeState.Idle -> {}
            }
        }
    }
}