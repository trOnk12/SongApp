package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Observable
import io.reactivex.Single

class GetCombinedSongsUseCase(private val songRepository: SongRepository) {

    fun getSongs(artistName: String, releaseYear: String?): Observable<MutableList<MutableList<SongItemViewModel>>> {
        return if (releaseYear == null) {
            getSongsByArtistName(artistName)!!
        } else {
            getSongsByArtistNameAndReleaseYear(artistName, releaseYear)!!
        }
    }

    private fun getSongsByArtistName(artistName: String): Observable<MutableList<MutableList<SongItemViewModel>>>? {
        val localSongsItemView = songRepository.getLocalSongs(artistName)
            .flatMapIterable { list -> list }
            .map { item -> SongItemViewModel.convertFromLocal(item) }
            .toList()
            .toObservable()

        val remoteSongsItemView = songRepository.getRemoteSongs(artistName)
            .flatMapIterable { list -> list }
            .map { item -> SongItemViewModel.convertFromRemote(item) }
            .toList()
            .toObservable()

        return localSongsItemView.mergeWith(remoteSongsItemView).toList().toObservable()
    }

    private fun getSongsByArtistNameAndReleaseYear(
        artistName: String,
        releaseYear: String
    ): Observable<MutableList<MutableList<SongItemViewModel>>>? {
        val localSongsItemView = songRepository.getLocalSongs(artistName, releaseYear)
            .flatMapIterable { list -> list }
            .map { item -> SongItemViewModel.convertFromLocal(item) }
            .toList()
            .toObservable()

        val remoteSongsItemView = songRepository.getRemoteSongs(artistName, releaseYear)
            .flatMapIterable { list -> list }
            .map { item -> SongItemViewModel.convertFromRemote(item) }
            .toList()
            .toObservable()

        return localSongsItemView.mergeWith(remoteSongsItemView).toList().toObservable()!!
    }

}