package com.example.abhishek.mvvmexamplekotlin.base

/**
 * Created by abhishek on 28/08/17.
 */

abstract class BaseRecyclerViewAdapter<T, V : androidx.recyclerview.widget.RecyclerView.ViewHolder>(
  protected var mList: List<T>?
) : androidx.recyclerview.widget.RecyclerView.Adapter<V>() {

  override fun getItemCount(): Int {
    mList?.let { return it.size }
    return 0
  }

  fun setData(mList: List<T>) {
    this.mList = mList
    notifyDataSetChanged()
  }

  fun getItem(position: Int): T? {
    mList?.let {
      return it.getOrNull(position)
    }
    return null
  }
}
