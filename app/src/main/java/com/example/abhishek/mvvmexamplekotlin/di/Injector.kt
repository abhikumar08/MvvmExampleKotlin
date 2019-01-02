package com.example.abhishek.mvvmexamplekotlin.di

import com.example.abhishek.mvvmexamplekotlin.App

object Injector {

    lateinit var appComponent:AppComponent

    fun init (app: App){
        appComponent  = DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
        appComponent.inject(app)
    }


}