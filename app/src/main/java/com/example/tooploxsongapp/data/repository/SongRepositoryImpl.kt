package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.data.entities.CombinedSongs
import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class SongRepositoryImpl(private val remote: SongsRemoteImpl, private val local: SongsLocalImpl) : SongRepository {

    override fun getSongs(artistName: String): Flowable<CombinedSongs> {

        return Flowable.zip<List<LocalSong>, List<RemoteSong>, CombinedSongs>(local.getSongs(artistName),
            remote.getSongs(artistName),
            BiFunction { localSongs, remoteSongs ->
                CombinedSongs(localSongs, remoteSongs)
            })
    }

    override fun getLocalSongs(artistName: String): Flowable<List<LocalSong>> {
        return local.getSongs(artistName)
    }

    override fun getRemoteSongs(artistName: String): Flowable<List<RemoteSong>> {
        return remote.getSongs(artistName)
    }

    override fun getSongs(artistName: String, releaseYear: String): Flowable<CombinedSongs> {
        return Flowable.zip<List<LocalSong>, List<RemoteSong>, CombinedSongs>(local.getSongs(artistName),
            remote.getSongs(artistName),
            BiFunction { localSongs, remoteSongs ->
                CombinedSongs(localSongs, remoteSongs)
            })
    }

    override fun getLocalSongs(artistName: String, releaseYear: String): Flowable<List<LocalSong>> {
        return local.getSongs(artistName)
    }

    override fun getRemoteSongs(artistName: String, releaseYear: String): Flowable<List<RemoteSong>> {
        return remote.getSongs(artistName)
    }

}