package com.example.abhishek.mvvmexamplekotlin.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.abhishek.mvvmexamplekotlin.common.BASE_URL
import com.example.abhishek.mvvmexamplekotlin.database.PostsDb
import com.example.abhishek.mvvmexamplekotlin.network.PostsApi
import com.example.abhishek.mvvmexamplekotlin.model.PostsDao
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule(internal var application: Application){

    @Provides
    @Singleton
    internal fun providesPostRetrofitService(retrofit: Retrofit): PostsApi {
        return retrofit.create(PostsApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): PostsDb {
        return Room
            .databaseBuilder(app, PostsDb::class.java, "posts.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostsDao(db: PostsDb): PostsDao {
        return db.postDao()
    }

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun providesContext(application: Application): Context {
        return application.applicationContext
    }

}