package com.example.abhishek.mvvmexamplekotlin.posts

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.abhishek.mvvmexamplekotlin.R
import com.example.abhishek.mvvmexamplekotlin.databinding.ItemPostBinding
import com.example.abhishek.mvvmexamplekotlin.base.BaseRecyclerViewAdapter
import com.example.abhishek.mvvmexamplekotlin.model.Post

class PostListAdapter(var postList: List<Post>) :
    BaseRecyclerViewAdapter<Post, PostListAdapter.ViewHolder>(postList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList?.get(position)!!)
    }

     class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
         private val viewModel = PostViewModel()

         fun bind(post: Post) {
             viewModel.bind(post)
             binding.viewModel = viewModel
         }
     }
}