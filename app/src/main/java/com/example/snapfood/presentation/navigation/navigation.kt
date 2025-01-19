package com.example.snapfood.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.snapfood.presentation.ui.details.DetailsScreen

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.snapfood.presentation.ui.search.SearchScreen
import com.example.snapfood.presentation.ui.search.SearchScreenEvent
import com.example.snapfood.presentation.ui.search.SearchScreenNavigation
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

//        Collect Navigation Events
        LaunchedEffect(true) {
            viewModel.navigation.collect { navigationEvent ->
                when (navigationEvent) {
                    is SearchScreenNavigation.NavigateToDetails -> {
                        navController.navigate(
                            Screen.Details.createRoute(navigationEvent.characterId)
                        )
                    }
                }
            }
        }
        SearchScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    is SearchScreenEvent.OnCharacterClick -> {
                        navController.navigate(
                            Screen.Details.createRoute(event.characterId)
                        )
                    }
                    else -> viewModel.onEvent(event)
                }
            }
        )
    }

    composable(
        route = Screen.Details.route,
    ) { backStackEntry ->
        val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
        DetailsScreen(
            characterName = characterId,
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}