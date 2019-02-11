package com.example.abhishek.mvvmexamplekotlin.ui.postlist.postitem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abhishek.mvvmexamplekotlin.model.Post

class PostViewModel : ViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post) {
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }
}