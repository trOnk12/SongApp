package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.data.entities.CombinedSongs
import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable

class GetCombinedSongsUseCase(private val songRepository: SongRepository) {

    fun getSongs(artistName: String, releaseYear: String): Flowable<CombinedSongs> {
       return songRepository.getSongs(artistName,releaseYear)
    }

}