package com.criticaltechworks.topheadlines.presentation.newslist

import com.criticaltechworks.topheadlines.BuildConfig
import com.criticaltechworks.topheadlines.News
import com.criticaltechworks.topheadlines.NewsEvent
import com.criticaltechworks.topheadlines.contract.NewsListContract
import com.criticaltechworks.topheadlines.interactor.NewsInteractor
import com.criticaltechworks.topheadlines.responsebody.ArticlesResponse
import com.criticaltechworks.topheadlines.responsebody.NewsSearchResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsListPresenterTest {

    companion object {
        private const val GENERIC_STRING = "generic_string"
    }

    @Mock
    lateinit var newsListInteractor: NewsInteractor

    @Mock
    lateinit var newsListView: NewsListContract.View

    @Mock
    lateinit var newsListRouter: NewsListContract.Router
    private lateinit var newsListPresenter: NewsListPresenter
    private lateinit var viewTestScheduler: TestScheduler
    private lateinit var networkTestScheduler: TestScheduler
    private lateinit var uiClickListener: PublishSubject<NewsEvent>
    private lateinit var newsList: List<News>
    private lateinit var articleList: List<ArticlesResponse>


    @Before
    fun setup() {
        viewTestScheduler = TestScheduler()
        networkTestScheduler = TestScheduler()
        newsListPresenter = NewsListPresenter().apply {
            view = newsListView
            router = newsListRouter
            newsInteractor = newsListInteractor
            disposables = CompositeDisposable()
            viewScheduler = viewTestScheduler
            networkScheduler = networkTestScheduler
        }

        uiClickListener = PublishSubject.create()

        articleList = listOf(
            ArticlesResponse(
                GENERIC_STRING, GENERIC_STRING, GENERIC_STRING,
                GENERIC_STRING, GENERIC_STRING
            )
        )
        newsList = listOf(
            News(
                GENERIC_STRING, GENERIC_STRING, GENERIC_STRING,
                GENERIC_STRING, GENERIC_STRING
            )
        )
    }

    @Test
    fun setupViewsTest() {
        newsListPresenter.setupViews()

        verify(newsListView).setupNewsProviderTitle(BuildConfig.NEWS_SOURCE)
    }

    @Test
    fun handleFetchNewsSuccessTest() {
        val expectedNewsSearchResponse = NewsSearchResponse(articleList)
        `when`(newsListInteractor.getNews()).thenReturn(Single.just(expectedNewsSearchResponse))
        `when`(newsListInteractor.mapResponseToNewsList(expectedNewsSearchResponse.newsArticles)).thenReturn(
            newsList
        )

        newsListPresenter.handleFetchNews()

        networkTestScheduler.triggerActions()
        viewTestScheduler.triggerActions()

        verify(newsListView).populateRecyclerView(newsList)
    }

    @Test
    fun handleFetchNews_handleErrorTest() {
        val expectedNewsSearchResponse = NewsSearchResponse(emptyList())
        `when`(newsListInteractor.getNews()).thenReturn(Single.just(expectedNewsSearchResponse))

        newsListPresenter.handleFetchNews()

        networkTestScheduler.triggerActions()
        viewTestScheduler.triggerActions()

        verify(newsListView).showNoNews()
    }

    @Test
    fun handleNewsClickTest() {
        `when`(newsListView.newsItemClicked()).thenReturn(uiClickListener)
        newsListPresenter.handleNewsClick()
        uiClickListener.onNext(NewsEvent(newsList[0]))
        viewTestScheduler.triggerActions()

        verify(newsListRouter).goToSelectedNews(newsList[0])
    }
}