import com.google.gson.annotations.SerializedName

data class LocalSong (

	@SerializedName("LocalSong Clean") val song : String,
	@SerializedName("ARTIST CLEAN") val artist : String,
	@SerializedName("Release Year") val releaseYear : Int,
	@SerializedName("COMBINED") val combined : String,
	@SerializedName("First?") val first : Int,
	@SerializedName("Year?") val year : Int,
	@SerializedName("PlayCount") val playCount : Int,
	@SerializedName("f*G") val f : Int

)