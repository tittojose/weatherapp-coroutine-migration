package weatherapp.tittojose.me.weatherapp.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import weatherapp.tittojose.me.weatherapp.model.WeatherAPI
import weatherapp.tittojose.me.weatherapp.view.HomeScreen

class HomeScreenPresenter(
	private val homeScreenView: HomeScreen,
	private val weatherApi: WeatherAPI
) {
	private val disposables: CompositeDisposable = CompositeDisposable()

	fun loadWeatherData() {
		val disposable = weatherApi.getCurrentWeather()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.doOnSubscribe { homeScreenView.showLoading() }
			.subscribe({
				homeScreenView.hideLoading()
				homeScreenView.onWeatherDataLoadSuccess(it)
			}, {
				homeScreenView.hideLoading()
				homeScreenView.onWeatherDataLoadFailed()
			})
		disposables.add(disposable)
	}

	fun retryLoadWeatherData() {
		loadWeatherData()
	}

	fun onDestroy() {
		disposables.dispose()
	}
}