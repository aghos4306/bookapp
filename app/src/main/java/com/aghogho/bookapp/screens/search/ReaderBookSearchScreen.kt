package com.aghogho.bookapp.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.aghogho.bookapp.ReaderApp
import com.aghogho.bookapp.components.ReaderAppBar
import com.aghogho.bookapp.navigation.ReaderScreens

@Preview
@Composable
fun BookSearchScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Search Books",
                icon = Icons.Default.ArrowBack,
                navController = navController,
                showProfile = false
            ) {
                navController.navigate(ReaderScreens.ReaderHomeScreen.name)
            }
        }
    ) {

    }
}