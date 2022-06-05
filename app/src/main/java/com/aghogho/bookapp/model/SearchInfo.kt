package com.aghogho.bookapp.model


import com.google.gson.annotations.SerializedName

data class SearchInfo(
    @SerializedName("textSnippet")
    val textSnippet: String
)