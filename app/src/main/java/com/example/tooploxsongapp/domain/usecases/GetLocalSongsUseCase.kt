package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable

class GetLocalSongsUseCase(private val songRepository: SongRepository) {

    fun getLocalSongsByArtistName(artistName:String) : Flowable<List<RemoteSong>> = songRepository.getLocalSongByArtist(artistName)

}