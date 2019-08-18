package com.example.tooploxsongapp.di.modules

import com.example.tooploxsongapp.data.api.RemoteSongsApi
import com.example.tooploxsongapp.data.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRemoteSongsApi(retrofit: Retrofit): RemoteSongsApi {
        return retrofit.create(RemoteSongsApi::class.java)
    }

    @Singleton
    @Provides
     fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

}