package com.example.abhishek.mvvmexamplekotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.abhishek.mvvmexamplekotlin.model.Post

@Database(entities = arrayOf(Post::class), version = 1)
abstract class PostsDb : RoomDatabase() {
    abstract fun postDao(): PostsDao
}