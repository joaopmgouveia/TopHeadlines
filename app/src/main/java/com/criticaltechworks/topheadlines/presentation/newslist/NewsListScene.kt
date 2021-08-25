package com.criticaltechworks.topheadlines.presentation.newslist

import com.criticaltechworks.topheadlines.BuildConfig
import com.criticaltechworks.topheadlines.api.NewsApi
import com.criticaltechworks.topheadlines.interactor.NewsInteractor
import com.criticaltechworks.topheadlines.service.NewsService
import com.criticaltechworks.topheadlines.view.newslist.NewsListActivity
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun NewsListActivity.createScene() {

    val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    val service = NewsService(builder.build().create(NewsApi::class.java))
    val interactor = NewsInteractor(service)

    val router = NewsListRouter().also {
        it.activity = this
    }

    val newsListPresenter = NewsListPresenter().also {
        it.view = this
        it.router = router
        it.newsInteractor = interactor
        it.disposables = CompositeDisposable()
        it.viewScheduler = AndroidSchedulers.mainThread()
        it.networkScheduler = Schedulers.io()
    }

    presenter = newsListPresenter
    uiClickListener = PublishSubject.create()
    adapter = NewsAdapter(emptyList(), uiClickListener)
}