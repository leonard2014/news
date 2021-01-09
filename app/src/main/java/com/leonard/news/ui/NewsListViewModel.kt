package com.leonard.news.ui

import androidx.lifecycle.*
import com.leonard.news.data.RelatedImagesItem
import com.leonard.news.network.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {

    private val _newsListLiveData = MutableLiveData<List<UINewsItem>>()
    val newsListLiveData: LiveData<List<UINewsItem>> = _newsListLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _showErrorLiveData = MutableLiveData<Unit>()
    val showErrorLiveData: LiveData<Unit> = _showErrorLiveData

    private val _showNewsLiveData = SingleLiveEvent<String>()
    val showNewsLiveData: LiveData<String> = _showNewsLiveData

    init {
        loadNewsList()
    }

    private fun loadNewsList() {
        _loadingLiveData.postValue(true)
        viewModelScope.launch {
            try {
                repository.getNewsList().map {
                    UINewsItem(
                        it.headline.orEmpty(),
                        it.theAbstract.orEmpty(),
                        it.byLine.orEmpty(),
                        it.url,
                        it.relatedImages?.getSmallestImageUrl()
                    )
                }.let(_newsListLiveData::postValue)
            } catch (error: Exception) {
                showErrorDialog()
            } finally {
                _loadingLiveData.postValue(false)
            }
        }
    }

    private fun showErrorDialog() {
        _showErrorLiveData.postValue(Unit)
    }

    fun onNewsItemClicked(url: String) {
        _showNewsLiveData.postValue(url)
    }

    private fun List<RelatedImagesItem>.getSmallestImageUrl(): String? {
        return this.minBy { it.height }?.url
    }
}

data class UINewsItem(
    val headline: String,
    val abstract: String,
    val byline: String,
    val newsUrl: String,
    val imageUrl: String? = null
)

class NewsListViewModelFactory
@Inject constructor(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            NewsListViewModel(repository) as T
        } else {
            throw IllegalArgumentException("unknown model class $modelClass")
        }
}