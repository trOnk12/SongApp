package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.domain.model.Song
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Flowable

class GetLocalSongsUseCase(private val songRepository: SongRepository) {

    fun getLocalSongsByArtistName(artistName:String) : Flowable<List<Song>> = songRepository.getLocalSongByArtist(artistName)

}