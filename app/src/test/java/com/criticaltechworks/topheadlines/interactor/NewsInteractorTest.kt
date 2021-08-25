package com.criticaltechworks.topheadlines.interactor

import com.criticaltechworks.topheadlines.BuildConfig
import com.criticaltechworks.topheadlines.News
import com.criticaltechworks.topheadlines.responsebody.ArticlesResponse
import com.criticaltechworks.topheadlines.responsebody.NewsSearchResponse
import com.criticaltechworks.topheadlines.service.NewsService
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsInteractorTest {

    @Mock
    lateinit var newsService: NewsService

    private lateinit var newsInteractor: NewsInteractor
    private lateinit var articleResponseList: List<ArticlesResponse>
    private lateinit var newsList: List<News>


    @Before
    fun setup() {
        newsInteractor = NewsInteractor(newsService)

        val article1 = ArticlesResponse(
            "title1", "image_url1",
            "description1", "content1", "date1"
        )
        val article2 = ArticlesResponse(
            "title2", "image_url2",
            "description2", "content2", "date2"
        )
        val article3 = ArticlesResponse(
            "title3", "image_url3",
            "description3", "content3", "date3"
        )

        articleResponseList = listOf(article1, article2, article3)

        val news1 = News(
            "title1", "image_url1",
            "description1", "content1", "date1"
        )
        val news2 = News(
            "title2", "image_url2",
            "description2", "content2", "date2"
        )
        val news3 = News(
            "title3", "image_url3",
            "description3", "content3", "date3"
        )

        newsList = listOf(news1, news2, news3)

    }

    @Test
    fun getNewsTest() {

        val expectedNewsSearchResponse = NewsSearchResponse(articleResponseList)

        `when`(newsService.getNewsFromSource(BuildConfig.NEWS_SOURCE_ID))
            .thenReturn(Single.just(expectedNewsSearchResponse))

        val testObserver = TestObserver<NewsSearchResponse>()

        newsInteractor.getNews().subscribe(testObserver)

        testObserver.assertNoErrors().assertValue { it == expectedNewsSearchResponse }
    }

    @Test
    fun mapResponseToNewsListTest() {
        val mappedNewsList = newsInteractor.mapResponseToNewsList(articleResponseList)
        Assert.assertTrue(mappedNewsList == newsList)
    }
}