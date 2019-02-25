package com.example.abhishek.mvvmexamplekotlin.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.abhishek.mvvmexamplekotlin.R

class ProfileFragment : Fragment() {

  private lateinit var binding: com.example.abhishek.mvvmexamplekotlin.databinding.ProfileFragmentBinding
  private lateinit var viewModel: ProfileViewModel

  companion object {
    fun newInstance() = ProfileFragment()
  }

  private val userId by lazy {
    arguments?.let {
      ProfileFragmentArgs.fromBundle(it)
          .userId
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this)
        .get(ProfileViewModel::class.java)
    binding.viewModel = viewModel
    userId?.let {
      viewModel.loadProfile(it)
    }
  }

}
