package com.example.mvvmdagger2rxkotlin.dagger.component

import com.example.mvvmdagger2rxkotlin.dagger.module.RemoteModule
import com.example.mvvmdagger2rxkotlin.dagger.module.viewModelFactory.ViewModelModule
import com.example.mvvmdagger2rxkotlin.ui.post.PostListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(postListFragment: PostListFragment)
}