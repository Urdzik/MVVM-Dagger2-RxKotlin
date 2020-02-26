package com.example.mvvmdagger2rxkotlin.dagger.module.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
    private var daggerViewModel: ViewModel? = null

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ViewModel::class.java) || ViewModel::class.java.isAssignableFrom(modelClass)) {
            daggerViewModel?.let { return it as T }
            daggerViewModel = viewModels[modelClass]?.get()

            return daggerViewModel as T
        }
        throw ExceptionInInitializerError("not assignable class $modelClass")

    }
}