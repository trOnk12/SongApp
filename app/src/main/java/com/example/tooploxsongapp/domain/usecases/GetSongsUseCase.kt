package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.domain.repository.SongRepository

class GetSongsUseCase(private val songRepository: SongRepository) {

    fun getSongsByArtistName(artistName: String) = songRepository.getSongByArtist(artistName)
}