package com.criticaltechworks.topheadlines.presentation.selectednews

import com.criticaltechworks.topheadlines.view.selectednews.SelectedNewsActivity

fun SelectedNewsActivity.createScene() {

    val presenter = SelectedNewsPresenter().also {
        it.view = this
    }

    this.presenter = presenter
}