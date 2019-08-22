package com.example.tooploxsongapp.data.local

import com.example.tooploxsongapp.data.entities.LocalData
import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.domain.model.RemoteSong
import com.google.gson.Gson

class LocalSongsReader(private val fileManager: FileManager) {

    val FILE_DIRECTORY = "songs_local.json"

    fun getSongs():List<LocalSong>{
        val songsListJson = fileManager.readJSONFromFile(FILE_DIRECTORY)
        val localData = Gson().fromJson(songsListJson, LocalData::class.java)
        return localData.localSongList
    }

}