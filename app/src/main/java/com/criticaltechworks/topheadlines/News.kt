package com.criticaltechworks.topheadlines

import java.io.Serializable

data class News(
    val title: String,
    val imageUrl: String,
    val description: String,
    val content: String,
    val date: String
) : Serializable