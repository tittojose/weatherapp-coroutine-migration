package weatherapp.tittojose.me.weatherapp.model.pojo

import com.google.gson.annotations.SerializedName

data class Location (
	@SerializedName("name")
	var locationName: String
)