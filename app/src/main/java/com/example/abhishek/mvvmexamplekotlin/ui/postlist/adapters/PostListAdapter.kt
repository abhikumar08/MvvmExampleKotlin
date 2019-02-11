package com.example.abhishek.mvvmexamplekotlin.ui.postlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.abhishek.mvvmexamplekotlin.R
import com.example.abhishek.mvvmexamplekotlin.base.BaseRecyclerViewAdapter
import com.example.abhishek.mvvmexamplekotlin.databinding.ItemPostBinding
import com.example.abhishek.mvvmexamplekotlin.model.Post
import com.example.abhishek.mvvmexamplekotlin.ui.postlist.postitem.PostViewModel


class PostListAdapter(postList: List<Post>, private val itemClickListener: View.OnClickListener) :
    BaseRecyclerViewAdapter<Post, PostListAdapter.ViewHolder>(postList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mList?.let {
            holder.bind(it[position])
        }
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(itemClickListener)
    }

    class ViewHolder(private val binding: ItemPostBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()

        fun bind(post: Post) {
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}