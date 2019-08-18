package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.domain.model.Song
import io.reactivex.Flowable

interface SongsDataStore {

    fun getSongs(artistName:String) : Flowable<List<Song>>
}