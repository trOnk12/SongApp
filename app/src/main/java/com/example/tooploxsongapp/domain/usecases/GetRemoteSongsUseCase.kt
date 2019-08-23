package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable

class GetRemoteSongsUseCase(private val songRepository: SongRepository) {

    fun getRemoteSongs(artistName: String, releaseYear: String): Flowable<List<RemoteSong>> {
        return songRepository.getRemoteSongs(artistName, releaseYear)
    }

}