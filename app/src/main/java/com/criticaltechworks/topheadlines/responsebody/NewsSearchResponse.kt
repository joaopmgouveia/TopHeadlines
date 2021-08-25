package com.criticaltechworks.topheadlines.responsebody

import com.google.gson.annotations.SerializedName

data class NewsSearchResponse(
    @SerializedName("articles") val newsArticles: List<ArticlesResponse>
)
