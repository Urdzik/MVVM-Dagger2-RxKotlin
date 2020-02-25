package com.example.mvvmdagger2rxkotlin.model

import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {
    @GET("book.json")
    fun getPost(): Single<List<Post>>
}