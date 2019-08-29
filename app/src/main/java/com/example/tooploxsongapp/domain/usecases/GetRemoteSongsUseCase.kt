package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Observable

class GetRemoteSongsUseCase(private val songRepository: SongRepository) {

    fun getRemoteSongs(artistName: String, releaseYear: String?): Observable<MutableList<SongItemViewModel>>{
        return if(releaseYear != null || releaseYear == "") {
            songRepository.getRemoteSongs(artistName, releaseYear)
                .flatMapIterable { list -> list }
                .map { item -> SongItemViewModel.convertFromRemote(item) }
                .toList()
                .toObservable()
        } else{
            songRepository.getRemoteSongs(artistName)
                .flatMapIterable { list -> list }
                .map { item -> SongItemViewModel.convertFromRemote(item) }
                .toList()
                .toObservable()
        }
    }

}