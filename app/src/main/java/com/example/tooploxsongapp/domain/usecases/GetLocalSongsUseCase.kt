package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable

class GetLocalSongsUseCase(private val songRepository: SongRepository) {

    fun getLocalSongs(artistName:String,releaseYear:String) : Flowable<List<LocalSong>> {
        return songRepository.getLocalSongs(artistName,releaseYear)
    }
}