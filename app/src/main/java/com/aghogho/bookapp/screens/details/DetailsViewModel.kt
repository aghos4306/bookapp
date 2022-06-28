package com.aghogho.bookapp.screens.details

import androidx.lifecycle.ViewModel
import com.aghogho.bookapp.data.Resource
import com.aghogho.bookapp.model.Item
import com.aghogho.bookapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: BookRepository
): ViewModel() {

    suspend fun getBookInfo(bookId: String): Resource<Item> {
        return repository.getBookInfo(bookId)
    }

}