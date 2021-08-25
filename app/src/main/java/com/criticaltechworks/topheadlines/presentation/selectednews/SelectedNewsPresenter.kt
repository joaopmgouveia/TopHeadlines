package com.criticaltechworks.topheadlines.presentation.selectednews

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import com.criticaltechworks.topheadlines.News
import com.criticaltechworks.topheadlines.contract.SelectedNewsContract
import com.criticaltechworks.topheadlines.enum.NavigationBundleKeys

class SelectedNewsPresenter : SelectedNewsContract.Presenter {

    override lateinit var view: SelectedNewsContract.View


    override fun present(bundle: Bundle?) {
        if (bundle != null)
            setupNewsInfo(bundle.get(NavigationBundleKeys.NEWS.bundleKey) as News)
        else
            view.showGenericError()
    }

    @VisibleForTesting
    fun setupNewsInfo(news: News) {
        view.setupNewsImage(news.imageUrl)
        view.setupNewsTitle(news.title)
        view.setupNewsDescription(news.description)
        view.setupNewsContent(news.content)
    }
}