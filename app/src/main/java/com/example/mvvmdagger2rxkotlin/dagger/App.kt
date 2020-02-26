package com.example.mvvmdagger2rxkotlin.dagger

import android.app.Application
import com.example.mvvmdagger2rxkotlin.dagger.component.AppComponent
import com.example.mvvmdagger2rxkotlin.dagger.component.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}