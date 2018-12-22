package com.example.abhishek.mvvmexamplekotlin.network

import com.example.abhishek.mvvmexamplekotlin.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostsApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}