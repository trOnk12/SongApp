package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable

class GetRemoteSongsUseCase(private val songRepository: SongRepository) {

    fun getRemoteSongsByArtistName(artistName:String) : Flowable<List<RemoteSong>> = songRepository.getRemoteSongByArtist(artistName)
}