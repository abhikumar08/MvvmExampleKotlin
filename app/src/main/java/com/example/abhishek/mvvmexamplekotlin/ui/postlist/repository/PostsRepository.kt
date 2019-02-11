package com.example.abhishek.mvvmexamplekotlin.ui.postlist.repository

import com.example.abhishek.mvvmexamplekotlin.database.PostsDao
import com.example.abhishek.mvvmexamplekotlin.model.Post
import com.example.abhishek.mvvmexamplekotlin.network.Api
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val api: Api,
    private val postsDao: PostsDao
) {


    fun getPostsFromDb(): Observable<List<Post>> {
        return postsDao.getPosts().filter { it.isNotEmpty() }
            .toObservable()
            .doOnNext {
                Timber.d("Dispatching ${it.size} users from DB...")
            }
    }

    fun getPostsFromApi(): Observable<List<Post>> {
        return api.getPosts()
            .doOnNext {
                Timber.d("Dispatching ${it.size} users from API...")
                storePostInDb(it)
            }
    }

    fun storePostInDb(posts: List<Post>) {
        Observable.fromCallable { postsDao.insertAll(posts) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Timber.d("Inserted ${posts.size} users from API in DB...")
            }
    }

}