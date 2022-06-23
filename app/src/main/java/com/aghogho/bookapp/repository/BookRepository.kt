package com.aghogho.bookapp.repository

import com.aghogho.bookapp.data.Resource
import com.aghogho.bookapp.model.Item
import com.aghogho.bookapp.network.BooksApi
import com.aghogho.bookapp.utils.DataOrException
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

//    private val dataOrException = DataOrException<List<Item>, Boolean, Exception>()
//    private val bookInfoDataOrException = DataOrException<Item, Boolean, Exception>()
//
//
//    suspend fun getBooks(searchQuery: String): DataOrException<List<Item>, Boolean, Exception> {
//        try {
//            dataOrException.loading = true
//            dataOrException.data = api.getAllBooks(searchQuery).items
//            if (dataOrException.data!!.isNotEmpty()) dataOrException.loading = false
//        } catch (exp: Exception) {
//            dataOrException.e = exp
//        }
//        return dataOrException
//    }
//
//    suspend fun getBookInfo(bookId: String): DataOrException<Item, Boolean, Exception> {
//        val response = try {
//            bookInfoDataOrException.loading = true
//            bookInfoDataOrException.data = api.getBookInfo(bookId = bookId)
//            if (bookInfoDataOrException.data.toString().isNotEmpty()) bookInfoDataOrException.loading = false
//            else {
//
//            }
//        } catch (exp: Exception) {
//            bookInfoDataOrException.e = exp
//        }
//        return bookInfoDataOrException
//    }

}