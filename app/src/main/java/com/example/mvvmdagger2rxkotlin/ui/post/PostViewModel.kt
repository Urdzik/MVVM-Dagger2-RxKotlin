package com.example.mvvmdagger2rxkotlin.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdagger2rxkotlin.dase.BaseViewModel
import com.example.mvvmdagger2rxkotlin.model.Post

class PostViewModel : BaseViewModel() {

    private var _postTitle = MutableLiveData<String>()
    val postTitle: LiveData<String>
        get() = _postTitle

    private var _postText = MutableLiveData<String>()
    val postText: LiveData<String>
        get() = _postText

    fun bind(post: Post) {
        _postTitle.value = post.title
        _postText.value = post.text
    }
}