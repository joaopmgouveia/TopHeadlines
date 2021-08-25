package com.criticaltechworks.topheadlines.presentation.newslist

import android.app.Activity
import android.content.Intent
import com.criticaltechworks.topheadlines.News
import com.criticaltechworks.topheadlines.contract.NewsListContract
import com.criticaltechworks.topheadlines.enum.NavigationBundleKeys
import com.criticaltechworks.topheadlines.view.selectednews.SelectedNewsActivity

class NewsListRouter : NewsListContract.Router {

    override lateinit var activity: Activity


    override fun goToSelectedNews(news: News) {
        activity.startActivity(
            Intent(activity, SelectedNewsActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .putExtra(NavigationBundleKeys.NEWS.bundleKey, news)
        )
    }
}