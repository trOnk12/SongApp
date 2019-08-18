package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.data.local.LocalDataSongs
import com.example.tooploxsongapp.domain.model.Song
import io.reactivex.Flowable

class SongsLocalImpl(private val localDataSongs: LocalDataSongs) {

    fun getSongs(artistName: String): Flowable<List<Song>> {

    }

}