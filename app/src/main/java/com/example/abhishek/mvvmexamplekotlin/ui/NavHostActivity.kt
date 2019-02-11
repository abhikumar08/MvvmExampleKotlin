package com.example.abhishek.mvvmexamplekotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.abhishek.mvvmexamplekotlin.R
import com.example.abhishek.mvvmexamplekotlin.model.Post
import com.example.abhishek.mvvmexamplekotlin.ui.postlist.PostsFragment
import com.example.abhishek.mvvmexamplekotlin.ui.postlist.PostsFragmentDirections

class NavHostActivity : AppCompatActivity(), PostsFragment.PostsFragmentInterface {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        navController = findNavController(R.id.nav_host_fragment)

        // Update action bar to reflect navigation

//        Injector.appComponent.inject(this)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
//        binding.postList.layoutManager = LinearLayoutManager(
//            this,
//            RecyclerView.VERTICAL, false
//        )
//        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PostListViewModel::class.java)
//        viewModel.errorMessage.observe(this, Observer { errorMessage ->
//            if (errorMessage != null) showError(errorMessage) else hideError()
//        })
//        binding.viewModel = viewModel
    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    override fun postItemClicked(post: Post) {
        navController.navigate(PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(post))
    }
}
