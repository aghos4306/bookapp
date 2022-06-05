package com.aghogho.bookapp.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val listOfBooks: MutableState<DataOrException<List<Item>, Boolean, Exception>>
      = mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        searchBooks("android")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isEmpty()) {
                return@launch
            }
            //listOfBooks.value.loading = true
            listOfBooks.value = repository.getBooks(query)
            Log.d("DATA", "searchBooks: ${listOfBooks.value.data.toString()}")
            //if (listOfBooks.value.data?.isNotEmpty() == true) listOfBooks.value.loading = false
            if (listOfBooks.value.data.toString().isNotEmpty()) listOfBooks.value.loading = false
        }
    }

}