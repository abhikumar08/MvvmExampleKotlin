package com.example.abhishek.mvvmexamplekotlin.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.abhishek.mvvmexamplekotlin.model.Post
import io.reactivex.Single

@Dao
interface PostsDao {

    @Query("SELECT * FROM post")
    fun getPosts(): Single<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Post>)
}