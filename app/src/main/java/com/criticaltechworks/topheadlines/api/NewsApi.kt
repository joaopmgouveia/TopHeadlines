package com.criticaltechworks.topheadlines.api

import com.criticaltechworks.topheadlines.responsebody.NewsSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines/")
    fun getNewsSearch(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): Single<NewsSearchResponse>
}