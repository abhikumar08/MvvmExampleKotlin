package com.example.abhishek.mvvmexamplekotlin.ui.user

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abhishek.mvvmexamplekotlin.di.Injector
import com.example.abhishek.mvvmexamplekotlin.model.User
import com.example.abhishek.mvvmexamplekotlin.ui.user.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel : ViewModel() {

  @Inject
  lateinit var userRepository: UserRepository

  @Inject
  lateinit var context: Context

  private lateinit var networkSubscription: Disposable

  val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

  val name: MutableLiveData<String> = MutableLiveData()
  val phone: MutableLiveData<String> = MutableLiveData()
  val email: MutableLiveData<String> = MutableLiveData()
  val website: MutableLiveData<String> = MutableLiveData()

  init {
    Injector.appComponent.inject(this)
  }

  fun loadProfile(userId: Int) {
    networkSubscription = userRepository.getUserDetail(userId).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { showLoading() }
        .doOnTerminate { hideLoading() }
        .subscribe({ user ->
          onUserFetched(user)
        }, {

        })

  }

  private fun hideLoading() {
    loadingVisibility.value = View.GONE
  }

  private fun showLoading() {
    loadingVisibility.value = View.VISIBLE
  }

  private fun onUserFetched(user: User?) {
    user?.let {
      name.value = it.name
      email.value = it.email
      phone.value = it.phone
      website.value = it.website
    }
  }

  override fun onCleared() {
    super.onCleared()
    networkSubscription.dispose()
  }


}
