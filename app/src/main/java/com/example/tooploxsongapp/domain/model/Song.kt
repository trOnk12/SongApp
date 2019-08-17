package com.example.tooploxsongapp.domain.model

import com.google.gson.annotations.SerializedName

data class Song (

	@SerializedName("wrapperType") val wrapperType : String,
	@SerializedName("kind") val kind : String,
	@SerializedName("artistId") val artistId : Int,
	@SerializedName("trackId") val trackId : Int,
	@SerializedName("artistName") val artistName : String,
	@SerializedName("trackName") val trackName : String,
	@SerializedName("trackCensoredName") val trackCensoredName : String,
	@SerializedName("artistViewUrl") val artistViewUrl : String,
	@SerializedName("trackViewUrl") val trackViewUrl : String,
	@SerializedName("previewUrl") val previewUrl : String,
	@SerializedName("artworkUrl30") val artworkUrl30 : String,
	@SerializedName("artworkUrl60") val artworkUrl60 : String,
	@SerializedName("artworkUrl100") val artworkUrl100 : String,
	@SerializedName("collectionPrice") val collectionPrice : Double,
	@SerializedName("trackPrice") val trackPrice : Double,
	@SerializedName("releaseDate") val releaseDate : String,
	@SerializedName("collectionExplicitness") val collectionExplicitness : String,
	@SerializedName("trackExplicitness") val trackExplicitness : String,
	@SerializedName("trackTimeMillis") val trackTimeMillis : Int,
	@SerializedName("country") val country : String,
	@SerializedName("currency") val currency : String,
	@SerializedName("primaryGenreName") val primaryGenreName : String,
	@SerializedName("contentAdvisoryRating") val contentAdvisoryRating : String
)