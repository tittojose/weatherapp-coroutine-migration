package weatherapp.tittojose.me.weatherapp.presenter

import kotlinx.coroutines.*
import weatherapp.tittojose.me.weatherapp.model.WeatherAPI
import weatherapp.tittojose.me.weatherapp.view.HomeScreen

class HomeScreenPresenter(
	private val homeScreenView: HomeScreen,
	private val weatherApi: WeatherAPI
) {
	lateinit var job: Job

	fun loadWeatherData() {
		job = CoroutineScope(Dispatchers.Main).launch {
			try {
				homeScreenView.showLoading()
				val weatherModel = withContext(Dispatchers.IO) {
					weatherApi.getCurrentWeather()
				}
				homeScreenView.onWeatherDataLoadSuccess(weatherModel)
			} catch (exception: Exception) {
				homeScreenView.onWeatherDataLoadFailed()
			} finally {
				homeScreenView.hideLoading()
			}
		}
	}

	fun retryLoadWeatherData() {
		loadWeatherData()
	}

	fun onDestroy() {
		job.cancel()
	}
}