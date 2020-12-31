package weatherapp.tittojose.me.weatherapp.view

import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel

interface HomeScreen {
	fun onWeatherDataLoadSuccess(weatherModel: WeatherModel)
	fun onWeatherDataLoadFailed()
	fun showLoading()
	fun hideLoading()
	fun onRetryButtonClicked()
}