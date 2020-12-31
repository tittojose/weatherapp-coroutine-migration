package weatherapp.tittojose.me.weatherapp.model.pojo

import com.google.gson.annotations.SerializedName

data class WeatherModel(
	@SerializedName("current") val current: Current,
	@SerializedName("location") val location: Location
)