//package com.example.snapfood.presentation.navigation
//
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.composable
//import com.example.snapfood.presentation.ui.search.SearchScreen
//import com.example.snapfood.presentation.ui.details.DetailsScreen
//import com.example.snapfood.presentation.ui.search.SearchScreenEvent
//
//package com.example.snapfood.presentation.navigation
//
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.composable
//import com.example.snapfood.presentation.ui.search.SearchScreen
//import com.example.snapfood.presentation.ui.search.SearchViewModel
//import com.example.snapfood.presentation.ui.details.DetailsScreen
//
//sealed class Screen(val route: String) {
//    object Search : Screen("search")
//    object Details : Screen("details/{characterId}") {
//        fun createRoute(characterId: String) = "details/$characterId"
//    }
//}
//
//fun NavGraphBuilder.starWarsNavGraph(navController: NavHostController) {
//    composable(route = Screen.Search.route) {
//        // Get ViewModel instance
//        val viewModel: SearchViewModel = hiltViewModel()
//        // Collect state
////        val state by viewModel.state.collectAsState()
//
////        SearchScreen(
////            state = state,
////            onEvent = { event ->
////                when (event) {
////                    is SearchScreenEvent.OnCharacterClick -> {
////                        navController.navigate(Screen.Details.createRoute(event.characterId))
////                    }
////                    else -> {}
//////                    else -> viewModel.onEvent(event)
////                }
////            }
////        )
//    }
//
//    composable(
//        route = Screen.Details.route,
//    ) { backStackEntry ->
//        val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
//        DetailsScreen(
//            characterName = characterId,
//            onBackClick = {
//                navController.popBackStack()
//            }
//        )
//    }
//}