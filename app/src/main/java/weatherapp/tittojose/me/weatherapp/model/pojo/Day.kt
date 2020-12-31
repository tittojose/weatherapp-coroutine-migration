package weatherapp.tittojose.me.weatherapp.model.pojo

import com.google.gson.annotations.SerializedName

data class Day(
	@SerializedName("avgtemp_c")
	val avgtempC: Float
)