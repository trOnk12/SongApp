package com.example.tooploxsongapp.domain.usecases

import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.domain.repository.SongRepository
import io.reactivex.Observable

class GetLocalSongsUseCase(private val songRepository: SongRepository) {

    fun getLocalSongs(artistName:String,releaseYear:String?) : Observable<MutableList<SongItemViewModel>> {
        return if(releaseYear == "" || releaseYear == null){
            songRepository.getLocalSongs(artistName)
                .flatMapIterable { list -> list }
                .map { item ->  SongItemViewModel.convertFromLocal(item) }
                .toList()
                .toObservable()
        } else {
            songRepository.getLocalSongs(artistName, releaseYear)
                .flatMapIterable { list -> list }
                .map { item -> SongItemViewModel.convertFromLocal(item) }
                .toList()
                .toObservable()
        }
    }
}