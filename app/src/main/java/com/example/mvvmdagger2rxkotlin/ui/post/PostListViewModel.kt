package com.example.mvvmdagger2rxkotlin.ui.post

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdagger2rxkotlin.R
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


    private var _loadingVisibility = MutableLiveData<Int>()
    val loadingVisibility: LiveData<Int>
        get() = _loadingVisibility


    private var _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    var errorClickListener = View.OnClickListener { lostPost() }


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
                {
                    Log.i("o2",it.toString())
                    onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart() {
        _loadingVisibility.value = View.VISIBLE
        _errorMessage.value = null

    }

    private fun onRetrievePostListFinish() {
        _loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess() {

    }

    private fun onRetrievePostListError() {
        _errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}