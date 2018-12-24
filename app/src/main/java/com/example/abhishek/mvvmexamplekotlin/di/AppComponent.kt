package com.example.abhishek.mvvmexamplekotlin.di

import com.example.abhishek.mvvmexamplekotlin.App
import com.example.abhishek.mvvmexamplekotlin.ui.posts.PostListViewModel
import com.example.abhishek.mvvmexamplekotlin.ui.posts.PostsActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {


    fun inject(app: App)
    fun inject(app: PostListViewModel)
    fun inject(postsActivity: PostsActivity)

}