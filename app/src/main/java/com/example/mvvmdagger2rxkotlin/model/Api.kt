package com.example.mvvmdagger2rxkotlin.model

import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET("book.json")
    fun getBook(): Single<List<Book>>
}