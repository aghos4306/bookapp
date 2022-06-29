package com.aghogho.bookapp.repository

import com.aghogho.bookapp.data.DataOrException
import com.aghogho.bookapp.model.MBook
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireRepository @Inject constructor(
    private val queryBook: Query
) {

    suspend fun getAllBooksFromFireStoreDatabase(): DataOrException<List<MBook>, Boolean, Exception> {
        val dataOrException = DataOrException<List<MBook>, Boolean, Exception>()
        try {
            dataOrException.loading = true
            dataOrException.data = queryBook.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MBook::class.java)!!
            }
            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
        } catch (exp: FirebaseFirestoreException) {
            dataOrException.e = exp
        }
        return dataOrException
    }

}