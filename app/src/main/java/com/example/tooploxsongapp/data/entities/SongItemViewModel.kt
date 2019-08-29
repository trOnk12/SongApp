package com.example.tooploxsongapp.data.entities

import com.example.tooploxsongapp.domain.model.RemoteSong

class SongItemViewModel(var title: String, var artistName : String, var releaseYear: String) {

    companion object {
        fun convertFromLocal(localSong: LocalSong): SongItemViewModel {
            return SongItemViewModel(
                localSong.song!!,
                localSong.artist!!,
                localSong.releaseYear.toString()
            )
        }

        fun convertFromRemote(remoteSong: RemoteSong): SongItemViewModel {
            return SongItemViewModel(
                remoteSong.trackName,
                remoteSong.artistName,
                remoteSong.releaseDate
            )
        }
    }

}