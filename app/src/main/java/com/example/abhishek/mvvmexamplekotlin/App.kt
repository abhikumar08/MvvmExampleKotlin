package com.example.abhishek.mvvmexamplekotlin

import android.app.Application
import com.example.abhishek.mvvmexamplekotlin.di.AppComponent
import com.example.abhishek.mvvmexamplekotlin.di.AppModule
import com.example.abhishek.mvvmexamplekotlin.di.DaggerAppComponent
import com.facebook.stetho.Stetho

class App : Application() {

    private var mAppComponent: AppComponent? = null

    val appComponent: AppComponent?
        get() {
            if (mAppComponent == null) {
                createAppComponent()
            }
            return mAppComponent
        }


    override fun onCreate() {
        super.onCreate()
        instance = this
        createAppComponent()
        mAppComponent?.inject(this)

        Stetho.initializeWithDefaults(this)

    }

    companion object {
        var instance: App? = null
            private set
    }

    private fun createAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}