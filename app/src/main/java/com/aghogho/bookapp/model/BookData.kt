package com.aghogho.bookapp.model


import com.google.gson.annotations.SerializedName

data class BookData(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int
)