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

//        LaunchedEffect(true) {
//            viewModel.navigation.collect { navigationEvent ->
//                when (navigationEvent) {
//                    is SearchScreenNavigation.NavigateToDetails -> {
//                        navController.navigate(
//                            Screen.Details.createRoute(navigationEvent.characterId)
//                        )
//                    }
//                }
//            }
//        }
        SearchScreen(
            state = state,
            onEvent = viewModel::onEvent,
            onNavigateToDetails = { characterId ->
                viewModel.onEvent(SearchScreenEvent.OnCharacterClick(characterId))
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