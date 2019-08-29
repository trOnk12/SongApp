package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.data.local.LocalSongsReader
import com.example.tooploxsongapp.domain.model.RemoteSong
import io.reactivex.Flowable

class SongsLocalImpl(private val localSongsReader: LocalSongsReader) : SongsDataStore<LocalSong> {

    override fun getSongs(artistName: String): Flowable<List<LocalSong>> {
        val localSongList = localSongsReader.getSongs()

        return Flowable.fromIterable(localSongList)
            .filter { song -> song.artist?.contains(artistName,true)!! }
            .toList()
            .toFlowable()
    }

    override fun getSongs(artistName: String, releaseYear: String): Flowable<List<LocalSong>> {
        val localSongList = localSongsReader.getSongs()

        return Flowable.fromIterable(localSongList)
            .filter { song -> song.artist?.contains(artistName,true)!! }
            .filter { song -> song.releaseYear == releaseYear.toInt()}
            .toList()
            .toFlowable()
    }


}