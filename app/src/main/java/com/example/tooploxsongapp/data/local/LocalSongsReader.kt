package com.example.tooploxsongapp.data.local

import com.example.tooploxsongapp.data.entities.LocalData
import com.example.tooploxsongapp.domain.model.Song
import com.google.gson.Gson

class LocalSongsReader(private val fileManager: FileManager) {

    val FILE_DIRECTORY = "songs_local.json"

    fun getSongs():List<Song>{
        val songsListJson = fileManager.readJSONFromFile(FILE_DIRECTORY)
        val localData = Gson().fromJson(songsListJson, LocalData::class.java)
        return localData.songList
    }

}