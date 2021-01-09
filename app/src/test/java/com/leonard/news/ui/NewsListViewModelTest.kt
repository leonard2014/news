package com.leonard.news.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leonard.news.data.AssetsItem
import com.leonard.news.data.RelatedImagesItem
import com.leonard.news.network.Repository
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import java.lang.Exception

class NewsListViewModelTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = TestCoroutineRule()

    private lateinit var repository: Repository

    private lateinit var viewModel: NewsListViewModel

    @Test
    fun `when loading succeeds should send news list to ui`() {
        val newsList = listOf(
            AssetsItem(
                headline = "headline 1",
                byLine = "byline 1",
                theAbstract = "abstract 1",
                url = "url1",
                relatedImages = listOf(
                    RelatedImagesItem(url = "imageUrl1", width = 300, height = 100)
                )
            ),
            AssetsItem(
                headline = "headline 2",
                byLine = "byline 2",
                theAbstract = "abstract 2",
                url = "url2",
                relatedImages = listOf(
                    RelatedImagesItem(url = "imageUrl2", width = 300, height = 100)
                )
            )
        )

        val uiNewsList = listOf(
            UINewsItem(
                headline = "headline 1",
                byline = "byline 1",
                abstract = "abstract 1",
                newsUrl = "url1",
                imageUrl = "imageUrl1"
            ),
            UINewsItem(
                headline = "headline 2",
                byline = "byline 2",
                abstract = "abstract 2",
                newsUrl = "url2",
                imageUrl = "imageUrl2"
            )
        )

        coroutineTestRule.runBlockingTest {
            repository = mock {
                onBlocking { getNewsList() } doReturn (newsList)
            }

            viewModel = NewsListViewModel(repository)

            val uiNewsItemObserver = viewModel.newsListLiveData.testObserver()

            assertThat(uiNewsItemObserver.observedValues.size).isEqualTo(1)
            assertThat(uiNewsItemObserver.observedValues[0]).isEqualTo(uiNewsList)
        }
    }

    @Test
    fun `when loading fails should show error`() {
        coroutineTestRule.runBlockingTest {
            repository = mock {
                onBlocking { getNewsList() } doAnswer { throw Exception() }
            }

            viewModel = NewsListViewModel(repository)

            val uiNewsItemObserver = viewModel.newsListLiveData.testObserver()
            val errorObserver = viewModel.showErrorLiveData.testObserver()

            assertThat(uiNewsItemObserver.observedValues.size).isEqualTo(0)
            assertThat(errorObserver.observedValues.size).isEqualTo(1)
        }
    }
}