package com.aghogho.bookapp.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aghogho.bookapp.data.Resource
import com.aghogho.bookapp.model.Item
import com.aghogho.bookapp.repository.BookRepository
import com.aghogho.bookapp.utils.DataOrException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val repository: BookRepository
    ): ViewModel() {

//    val listOfBooks: MutableState<DataOrException<List<Item>, Boolean, Exception>>
//      = mutableStateOf(DataOrException(null, true, Exception("")))
//
//    init {
//        searchBooks("java")
//    }

    var list: List<Item> by mutableStateOf(listOf())

    init {
        loadBooks()
    }

    private fun loadBooks() {
        searchBooks("software development")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                return@launch
            }
            try {
               when(val response = repository.getBooks(query)) {
                   is Resource.Success -> {
                       list = response.data!!
                   }
                   is Resource.Error -> {
                       Log.d("Network", "searchBooks: Failed to get books")
                   }
                   else -> {}
               }
            } catch (exp: Exception) {
                Log.d("Network", "searchBooks: ${exp.message.toString()}")
            }

//            listOfBooks.value.loading = true
//            listOfBooks.value = repository.getBooks(query)
//            Log.d("SEARCH", "searchBooks: ${listOfBooks.value.data}")
//            //if (listOfBooks.value.data?.isNotEmpty() == true) listOfBooks.value.loading = false
//            if (listOfBooks.value.data.toString().isNotEmpty()) listOfBooks.value.loading = false
        }
    }
}