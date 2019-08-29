package com.example.tooploxsongapp.data.entities

import com.google.gson.annotations.SerializedName


data class LocalSong(
    @SerializedName("Song Clean") val song: String?="",
    @SerializedName("ARTIST CLEAN") val artist: String?="",
    @SerializedName("Release Year") val releaseYear: Int?=0,
    @SerializedName("COMBINED") val combined: String?= "",
    @SerializedName("First?") val first: Int?=0,
    @SerializedName("Year?") val year: Int?=0,
    @SerializedName("PlayCount") val playCount: Int?=0,
    @SerializedName("f*G") val f: Int?=0
)