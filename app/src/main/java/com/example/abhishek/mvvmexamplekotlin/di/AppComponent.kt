package com.example.abhishek.mvvmexamplekotlin.di

import com.example.abhishek.mvvmexamplekotlin.App
import com.example.abhishek.mvvmexamplekotlin.ui.NavHostActivity
import com.example.abhishek.mvvmexamplekotlin.ui.postdetail.PostDetailViewModel
import com.example.abhishek.mvvmexamplekotlin.ui.postlist.PostsViewModel
import com.example.abhishek.mvvmexamplekotlin.ui.user.ProfileViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

  fun inject(app: App)
  fun inject(navHostActivity: NavHostActivity)
  fun inject(postsViewModel: PostsViewModel)
  fun inject(postDetailViewModel: PostDetailViewModel)
  fun inject(profileViewModel: ProfileViewModel)

  @Component.Builder
  interface Builder {
    fun build(): AppComponent

    fun appModule(appModule: AppModule): Builder
  }

}