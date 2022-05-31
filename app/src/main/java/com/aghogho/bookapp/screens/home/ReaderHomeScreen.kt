package com.aghogho.bookapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aghogho.bookapp.R
import com.aghogho.bookapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
                 ReaderAppBar(
                     title = "BookApp",
                     navController = navController
                 )
        },
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
fun ReaderAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {
    TopAppBar(
        title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (showProfile) {
                        //Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
                        Icon(
                            imageVector = Icons.Default.Book,
                            contentDescription = "Logo Icon",
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .scale(0.9f)
                        )
                    }
                    Text(
                        text = title,
                        color = Color.Red.copy(alpha = 0.7f),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.width(150.dp))
                }
        },
        actions = {
                  IconButton(onClick = {
                      FirebaseAuth.getInstance().signOut().run {
                          navController.navigate(ReaderScreens.LoginScreen.name)
                      }
                  }) {
                      Icon(
                          imageVector = Icons.Default.Logout,
                          contentDescription = "Logout Icon"
                      )
                  }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = { onTap() },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xFF92CBDF)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a Book",
            tint = Color.White
        )
    }
}
