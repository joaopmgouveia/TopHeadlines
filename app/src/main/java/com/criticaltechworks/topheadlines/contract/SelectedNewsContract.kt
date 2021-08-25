package com.criticaltechworks.topheadlines.contract

import android.os.Bundle

interface SelectedNewsContract {

    interface View {
        var presenter: Presenter
        fun setupNewsImage(imageUrl: String)
        fun setupNewsTitle(title: String)
        fun setupNewsDescription(description: String)
        fun setupNewsContent(content: String)
        fun showGenericError()
    }

    interface Presenter {
        var view: View
        fun present(bundle: Bundle?)
    }
}