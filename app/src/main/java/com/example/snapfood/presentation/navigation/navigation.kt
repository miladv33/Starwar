package com.example.snapfood.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.snapfood.presentation.ui.details.DetailsScreen

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.snapfood.presentation.ui.details.DetailsViewModel
import com.example.snapfood.presentation.ui.search.SearchScreen
import com.example.snapfood.presentation.ui.search.SearchViewModel

sealed class Screen(val route: String) {
    object Search : Screen("search")
    object Details : Screen("details/{characterId}") {
        fun createRoute(characterId: String) = "details/$characterId"
    }
}

fun NavGraphBuilder.starWarsNavGraph(navController: NavHostController) {
    composable(route = Screen.Search.route) {
        val viewModel: SearchViewModel = hiltViewModel()
        val state by viewModel.state.collectAsState()
        SearchScreen(
            state = state,
            onEvent = viewModel::onEvent,
            onNavigateToDetails = { characterId ->
                navController.navigate(Screen.Details.createRoute(characterId))
            }
        )
    }

    composable(
        route = Screen.Details.route,
    ) { backStackEntry ->
        val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
        val viewModel: DetailsViewModel = hiltViewModel()
        val state by viewModel.state.collectAsState()
        DetailsScreen(
            state = state,
            characterName = characterId,
            onEvent = viewModel::onEvent,
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}