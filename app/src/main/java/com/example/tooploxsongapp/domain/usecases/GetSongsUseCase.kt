package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.data.entities.CombinedSongs
import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable

class GetSongsUseCase(private val songRepository: SongRepository) {

    fun getSongs(artistName: String) = songRepository.getSongs(artistName)
    fun getLocalSongs(artistName: String) = songRepository.getLocalSongs(artistName)
    fun getRemoteSongs(artistName: String) = songRepository.getRemoteSongs(artistName)

    fun getSongs(artistName: String, releaseYear: String): Flowable<CombinedSongs> {
       return songRepository.getSongs(artistName,releaseYear)
    }

    fun getLocalSongs(artistName: String, releaseYear: String): Flowable<List<LocalSong>> {
        return songRepository.getLocalSongs(artistName,releaseYear)
    }

    fun getRemoteSongs(artistName: String, releaseYear: String): Flowable<List<RemoteSong>> {
        return songRepository.getRemoteSongs(artistName,releaseYear)
    }

}