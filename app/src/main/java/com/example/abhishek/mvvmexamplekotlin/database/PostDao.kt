package com.example.abhishek.mvvmexamplekotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

  @Query("SELECT * FROM post WHERE id = :id")
  fun getPostById(id: Int): Single<Post>

  @Update
  fun updatePost(post: Post)
}