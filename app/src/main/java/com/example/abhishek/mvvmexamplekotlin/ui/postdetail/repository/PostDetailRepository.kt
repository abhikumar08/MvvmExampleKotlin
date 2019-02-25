package com.example.abhishek.mvvmexamplekotlin.ui.postdetail.repository

import com.example.abhishek.mvvmexamplekotlin.database.PostsDao
import com.example.abhishek.mvvmexamplekotlin.model.Post
import com.example.abhishek.mvvmexamplekotlin.network.Api
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PostDetailRepository @Inject constructor(
  private val api: Api,
  private val postsDao: PostsDao
) {

  fun getPostDetailFromApi(id: Int): Observable<Post> {
    return api.getPostDetail(id)
        .doOnNext {
          Timber.d("Post fetched from API...")
          updatePost(it)
        }
  }

  fun getPostDetailFromDb(id: Int): Observable<Post> {
    return postsDao.getPostById(id)
        .toObservable()
        .doOnNext {
          Timber.d("Post ${it.id} fetched from db")
        }
  }

  fun updatePost(post: Post) {
    Observable.fromCallable { postsDao.updatePost(post) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Timber.d("Updated post with id ${post.id} from API in DB...")
        }
  }
}