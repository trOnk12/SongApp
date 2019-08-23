package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.domain.model.RemoteSong
import io.reactivex.Flowable

interface SongsDataStore<T> {

    fun getSongs(artistName:String) : Flowable<List<T>>

}