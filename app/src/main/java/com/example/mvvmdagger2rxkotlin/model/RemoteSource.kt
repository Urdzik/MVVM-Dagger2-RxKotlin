package com.example.mvvmdagger2rxkotlin.model

import io.reactivex.Single
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val api: PostApi
) {
    fun postsDataSingle(): Single<List<Post>> = api.getPost()
}