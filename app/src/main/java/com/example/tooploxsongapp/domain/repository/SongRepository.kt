package com.example.tooploxsongapp.domain.repository

import com.example.tooploxsongapp.data.entities.CombinedSongs
import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.domain.model.RemoteSong
import io.reactivex.Flowable

interface SongRepository {

    fun getSongs(artistName: String): Flowable<CombinedSongs>
    fun getLocalSongs(artistName: String): Flowable<List<LocalSong>>
    fun getRemoteSongs(artistName: String): Flowable<List<RemoteSong>>

    fun getSongs(artistName: String,releaseYear:String): Flowable<CombinedSongs>
    fun getLocalSongs(artistName: String,releaseYear: String): Flowable<List<LocalSong>>
    fun getRemoteSongs(artistName: String,releaseYear:String): Flowable<List<RemoteSong>>

}