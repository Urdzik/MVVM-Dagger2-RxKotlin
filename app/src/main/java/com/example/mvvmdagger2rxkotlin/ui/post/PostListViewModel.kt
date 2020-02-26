package com.example.mvvmdagger2rxkotlin.ui.post

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdagger2rxkotlin.R
import com.example.mvvmdagger2rxkotlin.model.Post
import com.example.mvvmdagger2rxkotlin.model.RemoteSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PostListViewModel @Inject constructor(
    private val remoteSource: RemoteSource
) : ViewModel() {

    private lateinit var subscription: Disposable

    private var _loadingVisibility = MutableLiveData<Int>()
    val loadingVisibility: LiveData<Int>
        get() = _loadingVisibility

    private var _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    private var _postsLiveData = MutableLiveData<List<Post>>()
    val postsLiveData: LiveData<List<Post>>
        get() = _postsLiveData

    var errorClickListener = View.OnClickListener { lostPost() }


    init {
        lostPost()
    }

    private fun lostPost() {
        subscription = remoteSource.postsDataSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart() {
        _loadingVisibility.value = View.VISIBLE
        _errorMessage.value = null

    }

    private fun onRetrievePostListFinish() {
        _loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(posts: List<Post>) {
        _postsLiveData.postValue(posts)
    }

    private fun onRetrievePostListError() {
        _errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}