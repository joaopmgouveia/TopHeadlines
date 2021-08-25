package com.criticaltechworks.topheadlines.presentation.newslist

import androidx.annotation.VisibleForTesting
import com.criticaltechworks.topheadlines.BuildConfig
import com.criticaltechworks.topheadlines.contract.NewsListContract
import com.criticaltechworks.topheadlines.interactor.NewsInteractor
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class NewsListPresenter : NewsListContract.Presenter {

    override lateinit var view: NewsListContract.View
    override lateinit var router: NewsListContract.Router
    override lateinit var newsInteractor: NewsInteractor
    override lateinit var disposables: CompositeDisposable
    override lateinit var viewScheduler: Scheduler
    override lateinit var networkScheduler: Scheduler


    override fun present() {
        setupViews()
        handleBiometricAuthentication()
        handleFetchNews()
        handleNewsClick()
    }

    override fun cleanDisposables() {
        disposables.dispose()
    }

    private fun handleBiometricAuthentication() {

    }

    @VisibleForTesting
    fun handleFetchNews() {
        disposables.add(
            newsInteractor.getNews()
                .subscribeOn(networkScheduler)
                .observeOn(viewScheduler)
                .doOnSuccess {
                    if (it.newsArticles.isNotEmpty()) {
                        view.populateRecyclerView(
                            newsInteractor.mapResponseToNewsList(it.newsArticles)
                        )
                        view.hideLoading()
                    } else {
                        view.showNoNews()
                    }
                }
                .subscribe({}, { handleError(it) })
        )
    }

    @VisibleForTesting
    fun handleNewsClick() {
        disposables.add(
            view.newsItemClicked()
                .observeOn(viewScheduler)
                .map {
                    router.goToSelectedNews(it.news)
                }
                .subscribe()
        )
    }

    @VisibleForTesting
    fun setupViews() {
        view.setupNewsProviderTitle(BuildConfig.NEWS_SOURCE)
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
        view.hideLoading()
        view.showNoNews()
    }
}