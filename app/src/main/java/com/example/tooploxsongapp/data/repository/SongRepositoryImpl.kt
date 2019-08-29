package com.example.tooploxsongapp.data.repository

import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(private val remote: SongsRemoteImpl, private val local: SongsLocalImpl) : SongRepository {

    override fun getLocalSongs(artistName: String): Flowable<List<LocalSong>> {
        return local.getSongs(artistName)
    }

    override fun getRemoteSongs(artistName: String): Flowable<List<RemoteSong>> {
        return remote.getSongs(artistName)
    }

    override fun getLocalSongs(artistName: String, releaseYear: String): Flowable<List<LocalSong>> {
        return local.getSongs(artistName,releaseYear)
    }

    override fun getRemoteSongs(artistName: String, releaseYear: String): Flowable<List<RemoteSong>> {
        return remote.getSongs(artistName)
            .flatMapIterable { list -> list }
            .filter { song -> song.releaseDate.contains(releaseYear) }
            .toList()
            .toFlowable()
    }

}