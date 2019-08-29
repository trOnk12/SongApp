package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.data.api.RemoteSongsApi
import com.example.tooploxsongapp.domain.model.RemoteSong
import io.reactivex.Flowable

class SongsRemoteImpl(private val api: RemoteSongsApi) : SongsDataStore<RemoteSong> {

    override fun getSongs(artistName: String, releaseYear: String): Flowable<List<RemoteSong>> =
        api.getSong(artistName, releaseYear).map {it.results}

    override fun getSongs(artistName: String): Flowable<List<RemoteSong>> =
        api.getSong(artistName,RemoteSongsApi.MUSIC).map{it.results}

}