package com.example.abhishek.mvvmexamplekotlin.common

import androidx.recyclerview.widget.RecyclerView
import android.view.View


open class BaseRecyclerViewHolder<T>(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

     fun bindData(data: T){

     }
}