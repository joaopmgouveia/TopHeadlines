package com.criticaltechworks.topheadlines.service

import com.criticaltechworks.topheadlines.BuildConfig
import com.criticaltechworks.topheadlines.api.NewsApi
import com.criticaltechworks.topheadlines.responsebody.ArticlesResponse
import com.criticaltechworks.topheadlines.responsebody.NewsSearchResponse
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsServiceTest {

    @Mock
    lateinit var api: NewsApi

    private lateinit var articleResponseList: List<ArticlesResponse>

    lateinit var newsService: NewsService


    @Before
    fun setup() {
        newsService = NewsService(api)

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
    }

    @Test
    fun getNewsFromSourceTest() {
        val testObserver = TestObserver<NewsSearchResponse>()

        val expectedNewsSearchResponse = NewsSearchResponse(articleResponseList)

        `when`(api.getNewsSearch(BuildConfig.NEWS_SOURCE_ID, BuildConfig.NEWS_API_KEY))
            .thenReturn(Single.just(expectedNewsSearchResponse))

        newsService.getNewsFromSource(BuildConfig.NEWS_SOURCE_ID).subscribe(testObserver)

        testObserver.assertNoErrors().assertValue { it == expectedNewsSearchResponse }
    }
}