package com.criticaltechworks.topheadlines.contract

import com.criticaltechworks.topheadlines.News
import com.criticaltechworks.topheadlines.NewsEvent
import com.criticaltechworks.topheadlines.interactor.NewsInteractor
import com.criticaltechworks.topheadlines.presentation.newslist.NewsAdapter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

interface NewsListContract {

    interface View : BaseViewContract {
        var presenter: Presenter
        var adapter: NewsAdapter
        var uiClickListener: PublishSubject<NewsEvent>
        fun setupNewsProviderTitle(title: String)
        fun populateRecyclerView(news: List<News>)
        fun newsItemClicked(): Observable<NewsEvent>
        fun showNoNews()
    }

    interface Presenter {
        var view: View
        var router: Router
        var newsInteractor: NewsInteractor
        var disposables: CompositeDisposable
        var viewScheduler: Scheduler
        var networkScheduler: Scheduler
        fun present()
        fun cleanDisposables()
    }

    interface Router : BaseRouterContract {
        fun goToSelectedNews(news: News)
    }
}