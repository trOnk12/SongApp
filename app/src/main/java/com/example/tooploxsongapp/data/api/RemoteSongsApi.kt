package com.example.tooploxsongapp.data.api

import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.model.Response
import io.reactivex.Flowable
import retrofit2.http.GET

interface RemoteSongsApi {

    //https://itunes.apple.com/search?term=jack+johnson&entity=musicVideo
    @GET("search?term=jack+johnson&entity=musicVideo")
    fun getSong(): Flowable<Response>

    @GET("search?term=jack+johnson&entity=musicVideo")
    fun getSong(artistName:String,releaseYear:String): Flowable<Response>

}