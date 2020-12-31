package weatherapp.tittojose.me.weatherapp.model

import io.reactivex.Single
import retrofit2.http.GET
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel

interface WeatherAPI {
	@GET("current?access_key=d96017976d32359af3e9394810025dcf&query=bengaluru")
	suspend fun getCurrentWeather(): WeatherModel
}