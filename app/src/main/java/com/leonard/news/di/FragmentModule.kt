package com.leonard.news.di

import com.leonard.news.ui.NewsFragment
import com.leonard.news.ui.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsFragment(): NewsFragment
}