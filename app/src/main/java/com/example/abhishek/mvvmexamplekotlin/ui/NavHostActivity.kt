package com.example.abhishek.mvvmexamplekotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.abhishek.mvvmexamplekotlin.R
import com.example.abhishek.mvvmexamplekotlin.model.Post
import com.example.abhishek.mvvmexamplekotlin.ui.postdetail.PostDetailFragment.PostDetailFragmentInterFace
import com.example.abhishek.mvvmexamplekotlin.ui.postdetail.PostDetailFragmentDirections
import com.example.abhishek.mvvmexamplekotlin.ui.postlist.PostsFragment
import com.example.abhishek.mvvmexamplekotlin.ui.postlist.PostsFragmentDirections

class NavHostActivity : AppCompatActivity(),
    PostsFragment.PostsFragmentInterface,
    PostDetailFragmentInterFace {

  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_posts)

    navController = findNavController(R.id.nav_host_fragment)
    setupActionBarWithNavController(navController)
  }

  override fun onSupportNavigateUp(): Boolean {
    return findNavController(R.id.nav_host_fragment).navigateUp()
  }

  override fun postItemClicked(post: Post) {
    navController.navigate(PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(post))
  }

  override fun onViewAuthorClicked(userId: Int) {
    navController.navigate(
        PostDetailFragmentDirections.actionPostDetailFragmentToProfileFragment(userId)
    )
  }
}
