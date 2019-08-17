package com.example.tooploxsongapp.domain.repository

import com.example.tooploxsongapp.domain.model.Song
import io.reactivex.Flowable

interface SongRepository {
    fun getSongByArtist(artistName:String) : Flowable<List<Song>>
    fun getLocalSongByArtist(artistName: String) : Flowable<List<Song>>
    fun getRemoteSongByArtist(artistName: String) : Flowable<List<Song>>
}