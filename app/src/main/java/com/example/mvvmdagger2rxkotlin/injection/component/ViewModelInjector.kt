package com.example.mvvmdagger2rxkotlin.injection.component

import com.example.mvvmdagger2rxkotlin.injection.module.NetworkModule
import com.example.mvvmdagger2rxkotlin.ui.post.PostListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {
    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}