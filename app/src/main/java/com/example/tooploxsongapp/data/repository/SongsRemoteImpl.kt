package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.data.api.RemoteSongsApi
import com.example.tooploxsongapp.domain.model.Song
import io.reactivex.Flowable

class SongsRemoteImpl(private val api: RemoteSongsApi) : SongsDataStore {

    override fun getSongs(artistName: String): Flowable<List<Song>> = api.getSongByArtistName(artistName)

}