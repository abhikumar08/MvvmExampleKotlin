package com.example.abhishek.mvvmexamplekotlin.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.example.abhishek.mvvmexamplekotlin.ui.posts.PostListViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            return PostListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}