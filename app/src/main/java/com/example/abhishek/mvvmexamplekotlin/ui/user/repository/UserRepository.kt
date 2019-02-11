package com.example.abhishek.mvvmexamplekotlin.ui.user.repository

import com.example.abhishek.mvvmexamplekotlin.model.User
import com.example.abhishek.mvvmexamplekotlin.network.Api
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: Api) {

    fun getUserDetail(id: Int): Observable<User> {
        return api.getUserDetail(id)
            .doOnNext {
                Timber.d("Dispatching user detail of ${it.id} from API...")

            }
    }
}