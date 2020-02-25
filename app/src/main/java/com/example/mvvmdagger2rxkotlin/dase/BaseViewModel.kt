package com.example.mvvmdagger2rxkotlin.dase

import androidx.lifecycle.ViewModel
import com.example.mvvmdagger2rxkotlin.injection.component.DaggerViewModelInjector
import com.example.mvvmdagger2rxkotlin.injection.component.ViewModelInjector
import com.example.mvvmdagger2rxkotlin.injection.module.NetworkModule
import com.example.mvvmdagger2rxkotlin.ui.post.PostListViewModel


abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}