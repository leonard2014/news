package com.leonard.news.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leonard.news.network.ApiService
import com.leonard.news.network.BASE_URL
import com.leonard.news.network.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {
    @Provides
    fun provideGson(): Gson =
        GsonBuilder().create()

    @Provides
    fun providesRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun providesRepository(apiService: ApiService) =
        Repository(apiService)
}