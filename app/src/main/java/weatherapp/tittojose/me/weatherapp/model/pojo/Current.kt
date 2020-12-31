package weatherapp.tittojose.me.weatherapp.model.pojo

import com.google.gson.annotations.SerializedName

data class Current(
	@SerializedName("temperature") val tempC: Float
	)