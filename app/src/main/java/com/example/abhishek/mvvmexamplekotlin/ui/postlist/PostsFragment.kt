package com.example.abhishek.mvvmexamplekotlin.ui.postlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abhishek.mvvmexamplekotlin.R
import com.example.abhishek.mvvmexamplekotlin.databinding.PostsFragmentBinding
import com.example.abhishek.mvvmexamplekotlin.model.Post
import com.example.abhishek.mvvmexamplekotlin.ui.NavHostActivity
import com.example.abhishek.mvvmexamplekotlin.ui.postlist.adapters.PostListAdapter
import com.google.android.material.snackbar.Snackbar


class PostsFragment : Fragment(), OnClickListener {


    private lateinit var binding: PostsFragmentBinding
    private var errorSnackbar: Snackbar? = null
    private var adapter: PostListAdapter =
        PostListAdapter(ArrayList(), this)
    private lateinit var postsFragmentInterface: PostsFragmentInterface

    companion object {
        fun newInstance() = PostsFragment()
    }

    private lateinit var viewModel: PostsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.posts_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
        binding.postList.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
        binding.postList.adapter = adapter

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.posts.observe(this, Observer { posts ->
            adapter.setData(posts)
        })
        binding.viewModel = viewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.postsFragmentInterface = context as NavHostActivity
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }


    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    override fun onClick(v: View?) {
        v?.let {
            val position = v?.tag as Int
            val selectedPost = adapter.getItem(position)
            selectedPost?.let { post ->
                postsFragmentInterface.postItemClicked(post)
            }
        }
    }


    interface PostsFragmentInterface {
        fun postItemClicked(post: Post)
    }
}
