package com.example.tooploxsongapp.data.local

import android.util.Log
import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.data.utils.IntTypeAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


class LocalSongsReader(private val fileManager: FileManager) {

    val FILE_DIRECTORY = "songs_local.json"

    fun getSongs():List<LocalSong>{
        val songsListJson = fileManager.readJSONFromFile(FILE_DIRECTORY)
        Log.d("TEST","local json" + songsListJson)

        val gsonInstance = GsonBuilder().registerTypeAdapter(java.lang.Integer::class.java, IntTypeAdapter()).create()

        val collectionType = object : TypeToken<Collection<LocalSong>>() {}.type
        val songList : Collection<LocalSong> = gsonInstance.fromJson(songsListJson, collectionType)

        return  songList as List<LocalSong>
    }

}