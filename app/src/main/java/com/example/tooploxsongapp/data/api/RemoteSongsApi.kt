package com.example.tooploxsongapp.data.api

import com.example.tooploxsongapp.domain.model.Song
import io.reactivex.Flowable
import retrofit2.http.GET

interface RemoteSongsApi {

    //https://itunes.apple.com/search?term=jack+johnson&entity=musicVideo
    @GET()
    fun getSongByArtistName(artistName:String): Flowable<List<Song>>

}