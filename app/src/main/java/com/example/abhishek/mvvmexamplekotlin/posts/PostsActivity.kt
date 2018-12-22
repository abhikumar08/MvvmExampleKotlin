package com.example.abhishek.mvvmexamplekotlin.posts

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.abhishek.mvvmexamplekotlin.R
import com.example.abhishek.mvvmexamplekotlin.databinding.ActivityPostsBinding
import com.example.abhishek.mvvmexamplekotlin.App
import com.example.abhishek.mvvmexamplekotlin.utils.ViewModelFactory

class PostsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostsBinding
    private lateinit var viewModel: PostListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance?.appComponent?.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    //
    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}
