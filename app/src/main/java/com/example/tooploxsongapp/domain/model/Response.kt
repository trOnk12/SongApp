package com.example.tooploxsongapp.domain.model

import com.google.gson.annotations.SerializedName

data class Response (

	@SerializedName("resultCount") val resultCount : Int,
	@SerializedName("results") val results : List<RemoteSong>

)