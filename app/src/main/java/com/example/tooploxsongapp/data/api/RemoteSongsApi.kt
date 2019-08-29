package com.example.tooploxsongapp.data.api

import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.model.Response
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteSongsApi {
    //https://itunes.apple.com/search?term=jack+johnson&entity=musicVideo
    @GET("search")
    fun getSong(@Query("term") artistName: String, @Query("entity") entity: String): Flowable<Response>

    @GET("search?term=jack+johnson&entity=musicVideo")
    fun getSong(artistName: String, releaseYear: String, entity: String): Flowable<Response>

    companion object {
        const val MUSIC: String = "musicVideo"
    }

}