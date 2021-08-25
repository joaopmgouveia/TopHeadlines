package com.criticaltechworks.topheadlines.service

import com.criticaltechworks.topheadlines.BuildConfig
import com.criticaltechworks.topheadlines.api.NewsApi
import com.criticaltechworks.topheadlines.responsebody.NewsSearchResponse
import io.reactivex.Single

class NewsService(private val newsApi: NewsApi) {


    fun getNewsFromSource(sources: String): Single<NewsSearchResponse> {
        return newsApi.getNewsSearch(sources, BuildConfig.NEWS_API_KEY)
    }
}