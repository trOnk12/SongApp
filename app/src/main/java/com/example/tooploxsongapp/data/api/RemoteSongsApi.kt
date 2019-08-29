package com.example.tooploxsongapp.data.api

import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.model.Response
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteSongsApi {
    companion object {
        const val MUSIC: String = "musicVideo"
    }

    @GET("search")
    fun getSong(@Query("term") artistName: String, @Query("entity") entity: String): Flowable<Response>
}