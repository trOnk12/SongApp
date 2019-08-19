package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.domain.model.Song
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable

class SongRepositoryImpl(private val remote: SongsRemoteImpl, private val local: SongsLocalImpl) : SongRepository {

    //Combines both local and remote repository data
    override fun getSongByArtist(artistName: String): Flowable<List<Song>> {
        return local.getSongs(artistName)
            .mergeWith(remote.getSongs(artistName))
    }

    override fun getLocalSongByArtist(artistName: String): Flowable<List<Song>> {
        return local.getSongs(artistName)
    }

    override fun getRemoteSongByArtist(artistName: String): Flowable<List<Song>> {
        return remote.getSongs(artistName)
    }

}