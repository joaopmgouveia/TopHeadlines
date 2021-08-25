package com.criticaltechworks.topheadlines.interactor

import com.criticaltechworks.topheadlines.BuildConfig
import com.criticaltechworks.topheadlines.News
import com.criticaltechworks.topheadlines.responsebody.ArticlesResponse
import com.criticaltechworks.topheadlines.responsebody.NewsSearchResponse
import com.criticaltechworks.topheadlines.service.NewsService
import io.reactivex.Single
import java.util.*

class NewsInteractor(private val newsService: NewsService) {


    fun getNews(): Single<NewsSearchResponse> {
        return newsService.getNewsFromSource(BuildConfig.NEWS_SOURCE_ID)
    }

    fun mapResponseToNewsList(newsResponseList: List<ArticlesResponse>): List<News> {
        val newsList: ArrayList<News> = ArrayList()
        for (response in newsResponseList) {
            newsList.add(
                News(
                    response.title,
                    response.imageUrl,
                    response.description,
                    response.content,
                    response.date
                )
            )
        }
        return newsList
    }

}