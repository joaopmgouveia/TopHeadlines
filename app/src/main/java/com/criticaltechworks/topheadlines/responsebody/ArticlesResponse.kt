package com.criticaltechworks.topheadlines.responsebody

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("title") val title: String,
    @SerializedName("urlToImage") val imageUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("content") val content: String,
    @SerializedName("publishedAt") val date: String
)
