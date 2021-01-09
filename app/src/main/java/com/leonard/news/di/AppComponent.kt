package com.leonard.news.di

import com.leonard.news.application.NewsApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        NetworkModule::class]
)
interface AppComponent : AndroidInjector<NewsApp> {
    @Component.Factory
    interface Factory {
        fun appComponent(): AppComponent
    }
}
