package com.example.mvvmdagger2rxkotlin.ui.post

import com.example.mvvmdagger2rxkotlin.dase.BaseViewModel
import com.example.mvvmdagger2rxkotlin.model.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    init {
        lostPost()
    }

    private fun lostPost() {
        subscription = postApi.getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { onRetrievePostListSuccess() },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart() {

    }

    private fun onRetrievePostListFinish() {

    }

    private fun onRetrievePostListSuccess() {

    }

    private fun onRetrievePostListError() {

    }


}