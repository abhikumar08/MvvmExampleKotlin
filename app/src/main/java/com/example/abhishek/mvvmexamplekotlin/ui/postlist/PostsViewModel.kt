package com.example.abhishek.mvvmexamplekotlin.ui.postlist

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abhishek.mvvmexamplekotlin.R
import com.example.abhishek.mvvmexamplekotlin.di.Injector
import com.example.abhishek.mvvmexamplekotlin.model.Post
import com.example.abhishek.mvvmexamplekotlin.ui.postlist.repository.PostsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel : ViewModel() {

  @Inject
  lateinit var postsRepository: PostsRepository

  private lateinit var networkSubscription: Disposable
  private lateinit var dbSubscription: Disposable

  val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
  val buttonVisibility: MutableLiveData<Int> = MutableLiveData()
  val errorMessage: MutableLiveData<Int> = MutableLiveData()
  val posts: MutableLiveData<List<Post>> = MutableLiveData()
  val errorClickListener = View.OnClickListener {
    loadPosts()
    buttonVisibility.value = View.GONE
  }

  init {
    Injector.appComponent.inject(this)
  }

  private fun loadPosts() {

    networkSubscription = postsRepository.getPostsFromApi().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { showLoading() }
        .doOnTerminate { hideLoading() }
        .subscribe(
            { postList -> onPostsSuccess(postList) },
            { onPostsError() }
        )

  }

  private fun hideLoading() {
    loadingVisibility.value = View.GONE
  }

  private fun showLoading() {
    loadingVisibility.value = View.VISIBLE
    errorMessage.value = null
  }

  private fun onPostsError() {
    dbSubscription = postsRepository.getPostsFromDb()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {}
        .doOnTerminate {}
        .subscribe(
            { postList ->
              onPostsFetchedFromDb(postList)
            }, {
          onErrorFetchingFromDb()
        }
        )
  }

  private fun onPostsSuccess(postList: List<Post>) {
    postsRepository.storePostInDb(postList)
    posts.value = postList
  }

  private fun onPostsFetchedFromDb(postList: List<Post>) {
    posts.value = postList
  }

  private fun onErrorFetchingFromDb() {
    errorMessage.value = R.string.post_error
  }

  override fun onCleared() {
    super.onCleared()
    networkSubscription.dispose()
    dbSubscription.dispose()
  }
}
