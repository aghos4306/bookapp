package com.aghogho.bookapp.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {},
        floatingActionButton = {
            FABContent{}
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            //Home Content
        }
    }
}

@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = { onTap() },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a Book",
            tint = MaterialTheme.colors.onSecondary
        )
    }
}
