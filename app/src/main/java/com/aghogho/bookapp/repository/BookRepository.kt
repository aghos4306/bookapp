package com.aghogho.bookapp.repository

import com.aghogho.bookapp.data.Resource
import com.aghogho.bookapp.model.Item
import com.aghogho.bookapp.network.BooksApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BooksApi) {

    suspend fun getBooks(searchQuery: String): Resource<List<Item>> {
        return try {
            Resource.Loading(data = true)
            val itemList = api.getAllBooks(searchQuery).items
            if (itemList.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(itemList)
        } catch (exp: Exception) {
            Resource.Error(message = exp.message.toString())
        }
    }

    suspend fun getBookInfo(bookId: String): Resource<Item> {
        val response = try {
            Resource.Loading(data = true)
            api.getBookInfo(bookId)
        } catch (exp: Exception) {
            return Resource.Error(message = "An unexpected error occurred ${exp.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }
}