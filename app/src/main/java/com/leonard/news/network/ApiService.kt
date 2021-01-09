package com.leonard.news.network

import com.leonard.news.data.NewsList
import retrofit2.http.GET


interface ApiService {
    @GET("1/coding_test/13ZZQX/full")
    suspend fun getNewsList() : NewsList
}