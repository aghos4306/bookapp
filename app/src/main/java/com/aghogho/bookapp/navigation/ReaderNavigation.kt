package com.aghogho.bookapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.aghogho.bookapp.screens.ReaderSplashScreen
import com.aghogho.bookapp.screens.details.BookDetailsScreen
import com.aghogho.bookapp.screens.home.HomeScreen
import com.aghogho.bookapp.screens.home.HomeScreenViewModel
import com.aghogho.bookapp.screens.login.LoginScreen
import com.aghogho.bookapp.screens.search.BookSearchScreen
import com.aghogho.bookapp.screens.search.BookSearchViewModel
import com.aghogho.bookapp.screens.stats.StatsScreen
import com.aghogho.bookapp.screens.update.BookUpdateScreen


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ReaderScreens.SplashScreen.name
    ) {
        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(navController = navController, viewModel = homeViewModel)
        }

        composable(ReaderScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name) {
            val searchViewModel = hiltViewModel<BookSearchViewModel>()
            BookSearchScreen(navController = navController, viewModel = searchViewModel)
        }

        composable(ReaderScreens.ReaderStatsScreen.name) {
            StatsScreen(navController = navController)
        }

        //set route to navigate to specific book details using the bookId
        val detailName = ReaderScreens.DetailScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId"){
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {
                BookDetailsScreen(navController = navController, bookId = it.toString())
            }
        }

        composable(ReaderScreens.UpdateScreen.name) {
            BookUpdateScreen(navController = navController)
        }
    }
}