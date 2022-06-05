package com.aghogho.bookapp.screens.home

import android.telecom.Call
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.aghogho.bookapp.R
import com.aghogho.bookapp.components.*
import com.aghogho.bookapp.model.MBook
import com.aghogho.bookapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Preview
@Composable
fun HomeScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(
        topBar = {
                 ReaderAppBar(
                     title = "BookApp",
                     navController = navController
                 )
        },
        floatingActionButton = {
            FABContent{
                navController.navigate(ReaderScreens.SearchScreen.name)
            }
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            //Home Content
            HomeContent(navController)
        }
    }
}

@Composable
fun ReadingRightNowArea(
    books: List<MBook>,
    navController: NavController
) {
    ListBookCard()
}

@Composable
fun HomeContent(
    navController: NavController
) {
    val listOfBooks = listOf(
        MBook("abc", "Once Upon", "James Brown", "Once Upon a time in Helsinki"),
        MBook("abd", "Surprise Me", "James Kenth", "Once Upon a time in Manchester"),
        MBook("abe", "First Strong Step", "Reva Klint", "Once Upon a time in London"),
        MBook("abf", "Android Dummy", "Austin Duff", "Learn Android Faster"),
        MBook("abg", "Jetpack Compose", "Tim Cooke", "Programmatically build your UI"),
    )
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty()) {
        FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
    } else {
        "Name n/a"
    }
    Column(
        Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .align(alignment = Alignment.Start)
        ) {
            TitleSection(label = " Your reading \n " +"activity right now...")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colors.secondaryVariant
                )
                Text(
                    text = currentUserName.toString(),
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Divider()
            }
        }
        //ListBookCard()
        ReadingRightNowArea(
            books = listOf(),
            navController = navController
        )
        TitleSection(label = "Reading List")

        BookListArea(
            listOfBooks = listOfBooks,
            navController = navController
        )
    }
}

@Composable
fun BookListArea(listOfBooks: List<MBook>, navController: NavController) {
    HorizontalScrollableComponent(listOfBooks) {
        Log.d("TAG", "BookListArea: $it")
        //Todo: Navigate to Book Details when Card is clicked.
    }
}

@Composable
fun HorizontalScrollableComponent(
    listOfBooks: List<MBook>,
    onCardPressed: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .horizontalScroll(scrollState)
    ) {
        for (book in listOfBooks) {
            ListBookCard(book) {
                onCardPressed(it)
            }
        }
    }
}


















