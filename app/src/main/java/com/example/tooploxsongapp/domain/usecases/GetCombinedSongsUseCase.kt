package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Observable

class GetCombinedSongsUseCase(private val songRepository: SongRepository) {

    fun getSongs(artistName: String, releaseYear: String): Observable<MutableList<SongItemViewModel>> {

        val localSongsItemView = songRepository.getLocalSongs(artistName,releaseYear)
            .flatMapIterable { list -> list }
            .map { item ->  SongItemViewModel.convertFromLocal(item) }
            .filter { item -> item.releaseYear == releaseYear }
            .toList()
            .toObservable()

        val remoteSongsItemView = songRepository.getRemoteSongs(artistName,releaseYear)
            .flatMapIterable { list -> list }
            .map { item ->  SongItemViewModel.convertFromRemote(item) }
            .toList()
            .toObservable()

          return Observable.concat(localSongsItemView,remoteSongsItemView)

    }

}