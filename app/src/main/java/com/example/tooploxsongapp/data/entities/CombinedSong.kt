package com.example.tooploxsongapp.data.entities

import com.example.tooploxsongapp.domain.model.RemoteSong

data class CombinedSongs(val localSong: List<LocalSong>, val remoteSong:List<RemoteSong>)
