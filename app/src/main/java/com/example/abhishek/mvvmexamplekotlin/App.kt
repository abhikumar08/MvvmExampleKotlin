package com.example.abhishek.mvvmexamplekotlin

import android.app.Application
import com.example.abhishek.mvvmexamplekotlin.di.Injector
import com.facebook.stetho.Stetho

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        Stetho.initializeWithDefaults(this)

    }

}