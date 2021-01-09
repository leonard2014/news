package com.leonard.news.network

import com.leonard.news.data.AssetsItem

class Repository(private val apiService: ApiService) {
    suspend fun getNewsList() : List<AssetsItem> {
        return apiService.getNewsList().assets.orEmpty()
    }
}