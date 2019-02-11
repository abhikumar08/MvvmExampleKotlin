package com.example.abhishek.mvvmexamplekotlin.network

import com.example.abhishek.mvvmexamplekotlin.model.Post
import com.example.abhishek.mvvmexamplekotlin.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/posts/{id}")
    fun getPostDetail(@Path("id") id: Int): Observable<Post>

    @GET("/users/{id}")
    fun getUserDetail(@Path("id") id: Int):Observable<User>
}