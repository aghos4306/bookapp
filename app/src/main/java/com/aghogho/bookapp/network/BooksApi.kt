package com.aghogho.bookapp.network

import com.aghogho.bookapp.model.BookData
import com.aghogho.bookapp.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BooksApi {

    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query: String): BookData

    @GET("volumes/{bookId}")
    suspend fun getBookInfo(@Path("bookId") bookId: String): Item

}