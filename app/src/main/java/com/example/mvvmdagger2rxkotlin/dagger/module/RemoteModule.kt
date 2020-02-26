package com.example.mvvmdagger2rxkotlin.dagger.module

import com.example.mvvmdagger2rxkotlin.model.PostApi
import com.example.mvvmdagger2rxkotlin.model.RemoteSource
import com.example.mvvmdagger2rxkotlin.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class RemoteModule {

    @Provides
    @Reusable
    internal fun provideRemoteSource(api: PostApi): RemoteSource = RemoteSource(api)

    @Provides
    @Reusable
    internal fun providePostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}