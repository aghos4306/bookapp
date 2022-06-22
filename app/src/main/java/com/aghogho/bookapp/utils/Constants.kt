package com.aghogho.bookapp.utils

import okhttp3.logging.HttpLoggingInterceptor

object Constants {

    const val BASE_URL = "https://www.googleapis.com/books/v1/"

    val LOGIN_INTERCEPTOR = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

}