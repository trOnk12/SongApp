package com.example.tooploxsongapp.domain.repository

import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.domain.model.RemoteSong
import io.reactivex.Flowable

interface SongRepository {
    fun getLocalSongs(artistName: String): Flowable<List<LocalSong>>
    fun getRemoteSongs(artistName: String): Flowable<List<RemoteSong>>
    fun getLocalSongs(artistName: String,releaseYear: String): Flowable<List<LocalSong>>
    fun getRemoteSongs(artistName: String,releaseYear:String): Flowable<List<RemoteSong>>
}