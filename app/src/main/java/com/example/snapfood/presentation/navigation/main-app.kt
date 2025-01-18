package com.example.snapfood.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.snapfood.presentation.theme.SnapFoodTheme

@Composable
fun StarWarsApp() {
    SnapFoodTheme {
        val navController = rememberNavController()
        
        NavHost(
            navController = navController,
            startDestination = Screen.Search.route
        ) {
            starWarsNavGraph(navController)
        }
    }
}