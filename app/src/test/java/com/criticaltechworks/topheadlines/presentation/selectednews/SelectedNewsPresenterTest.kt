package com.criticaltechworks.topheadlines.presentation.selectednews

import com.criticaltechworks.topheadlines.News
import com.criticaltechworks.topheadlines.contract.SelectedNewsContract
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SelectedNewsPresenterTest {

    companion object {
        private const val GENERIC_STRING = "generic_string"
    }

    @Mock
    private lateinit var selectedNewsView: SelectedNewsContract.View
    private lateinit var presenter: SelectedNewsPresenter
    private lateinit var news: News


    @Before
    fun setup() {
        presenter = SelectedNewsPresenter().apply { view = selectedNewsView }
        news = News(GENERIC_STRING, GENERIC_STRING, GENERIC_STRING, GENERIC_STRING, GENERIC_STRING)
    }

    @Test
    fun setupNewsInfoTest() {
        presenter.setupNewsInfo(news)
        verify(selectedNewsView).setupNewsImage(GENERIC_STRING)
        verify(selectedNewsView).setupNewsTitle(GENERIC_STRING)
        verify(selectedNewsView).setupNewsDescription(GENERIC_STRING)
        verify(selectedNewsView).setupNewsContent(GENERIC_STRING)
    }
}