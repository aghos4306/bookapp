package com.aghogho.bookapp.di

import com.aghogho.bookapp.network.BooksApi
import com.aghogho.bookapp.repository.BookRepository
import com.aghogho.bookapp.repository.FireRepository
import com.aghogho.bookapp.utils.Constants.BASE_URL
import com.aghogho.bookapp.utils.Constants.LOGIN_INTERCEPTOR
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFireBookRepository() =
        FireRepository(queryBook = FirebaseFirestore.getInstance().collection("books"))

    @Singleton
    @Provides
    fun provideBookApi(): BooksApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                    okhttp3.OkHttpClient.Builder()
                        .addInterceptor(LOGIN_INTERCEPTOR)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30,TimeUnit.SECONDS)
                        .connectTimeout(30,TimeUnit.SECONDS)
                        .build()
            )
            .build()
            .create(BooksApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBookRepository(api: BooksApi) = BookRepository(api)

}