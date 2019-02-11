package com.example.abhishek.mvvmexamplekotlin.ui.postdetail

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.abhishek.mvvmexamplekotlin.di.Injector
import com.example.abhishek.mvvmexamplekotlin.model.Post
import com.example.abhishek.mvvmexamplekotlin.ui.postdetail.repository.PostDetailRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostDetailViewModel : ViewModel() {

    @Inject
    lateinit var postDetailRepository: PostDetailRepository

    val postTitle = MutableLiveData<String>()
    val postBody = MutableLiveData<String>()


    private lateinit var networkSubscription: Disposable
    private lateinit var dbSubscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val post: MutableLiveData<Post> = MutableLiveData()

    init {
        Injector.appComponent.inject(this)
    }

    fun loadPostDetail() {
        if (post.value != null) {
            networkSubscription = postDetailRepository.getPostDetailFromApi(post.value!!.id).subscribeOn(
                Schedulers.io()
            )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showLoading() }
                .doOnTerminate { hideLoading() }
                .subscribe(
                    { postDetail -> onPostDetailSuccess(postDetail) },
                    { onPostDetailError() }
                )
        }

    }

    private fun onPostDetailSuccess(postDetail: Post) {

        post.value = postDetail
        postTitle.value = postDetail.title
        postBody.value = postDetail.body

    }

    private fun onPostDetailError() {

        dbSubscription = postDetailRepository.getPostDetailFromDb(post.value!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {}
            .doOnTerminate {}
            .subscribe(
                { postDetail ->
                    onPostDetailFetchedFromDb(postDetail)
                }, {
                    onErrorFetchingFromDb()
                }
            )

    }

    private fun onPostDetailFetchedFromDb(postDetail: Post) {

        post.value = postDetail
        postTitle.value = postDetail.title
        postBody.value = postDetail.body

    }

    private fun hideLoading() {
        loadingVisibility.value = View.GONE
    }

    private fun showLoading() {
        loadingVisibility.value = View.VISIBLE
        // errorMessage.value = null
    }

    private fun onErrorFetchingFromDb() {
        //errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        if (::networkSubscription.isInitialized)
            networkSubscription.dispose()
        if (::dbSubscription.isInitialized)
            dbSubscription.dispose()
    }
}
