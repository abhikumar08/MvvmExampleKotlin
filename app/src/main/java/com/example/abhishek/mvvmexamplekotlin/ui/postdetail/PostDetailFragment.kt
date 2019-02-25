package com.example.abhishek.mvvmexamplekotlin.ui.postdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.abhishek.mvvmexamplekotlin.R
import com.example.abhishek.mvvmexamplekotlin.databinding.PostDetailFragmentBinding
import com.example.abhishek.mvvmexamplekotlin.ui.NavHostActivity

class PostDetailFragment : Fragment() {

  companion object {
    fun newInstance() = PostDetailFragment()
  }

  private lateinit var viewModel: PostDetailViewModel
  private lateinit var binding: PostDetailFragmentBinding
  private lateinit var postDetailInterface: PostDetailFragmentInterFace

  private val post by lazy {
    arguments?.let {
      PostDetailFragmentArgs.fromBundle(it)
          .post
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater, R.layout.post_detail_fragment, container, false
    )

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this)
        .get(PostDetailViewModel::class.java)
    viewModel.post.value = this.post
    binding.viewModel = viewModel
    binding.buttonViewAuthor.setOnClickListener {
      post?.userId?.let {
        postDetailInterface.onViewAuthorClicked(userId = it)
      }
    }
    viewModel.loadPostDetail()
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    postDetailInterface = context as NavHostActivity
  }

  interface PostDetailFragmentInterFace {
    fun onViewAuthorClicked(userId: Int)
  }

}
