package com.example.tooploxsongapp.data.local

class LocalDataSongs(private val assetsHelper: AssetsHelper) {

    fun getSongs(){
        val songList = assetsHelper.readJSONFromFile("songs_local.json")

    }
}