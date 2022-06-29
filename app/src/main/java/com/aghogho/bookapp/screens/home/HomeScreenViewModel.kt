package com.aghogho.bookapp.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aghogho.bookapp.data.DataOrException
import com.aghogho.bookapp.model.MBook
import com.aghogho.bookapp.repository.FireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: FireRepository
): ViewModel() {

    val data: MutableState<DataOrException<List<MBook>, Boolean, Exception>> =
        mutableStateOf(DataOrException(listOf(), true, Exception("")))

    init {
        getAllBooksFromFirestoreDatabase()
    }

    private fun getAllBooksFromFirestoreDatabase() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllBooksFromFireStoreDatabase()
            if (!data.value.data.isNullOrEmpty()) data.value.loading = false
        }
        Log.d("GET", "getAllBooksFromFirestoreDatabase: ${data.value.data?.toList().toString()}")
    }

}