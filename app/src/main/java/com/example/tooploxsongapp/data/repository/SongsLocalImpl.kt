package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.data.local.LocalSongsReader
import com.example.tooploxsongapp.domain.model.Song
import io.reactivex.Flowable

class SongsLocalImpl(private val localSongsReader: LocalSongsReader) : SongsDataStore {

    override fun getSongs(artistName: String): Flowable<List<Song>> {
        val localSongList = localSongsReader.getSongs()

        return Flowable.fromIterable(localSongList)
            .filter { song -> song.artistName == artistName }
            .toList()
            .toFlowable()
    }

}