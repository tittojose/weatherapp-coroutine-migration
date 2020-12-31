package weatherapp.tittojose.me.weatherapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.android.synthetic.main.layout_weather.*
import weatherapp.tittojose.me.weatherapp.R
import weatherapp.tittojose.me.weatherapp.model.APIClient
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel
import weatherapp.tittojose.me.weatherapp.presenter.HomeScreenPresenter
import kotlin.math.roundToInt

class HomeScreenActivity : AppCompatActivity(), HomeScreen {

	private var startRotateAnimation: Animation? = null
	private var bottomUpAnimation: Animation? = null
	private lateinit var homeScreenPresenter: HomeScreenPresenter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home_screen)
		initializeAnimationObjects()
		btnRetry.setOnClickListener { onRetryButtonClicked() }

		homeScreenPresenter = HomeScreenPresenter(this, APIClient.weatherAPIClient)
		homeScreenPresenter.loadWeatherData()
	}

	override fun onWeatherDataLoadSuccess(weatherModel: WeatherModel) {
		layoutWeather.visibility = View.VISIBLE
		setCurrentWeatherData(weatherModel)
	}

	override fun onWeatherDataLoadFailed() {
		layoutError.visibility = View.VISIBLE
	}

	override fun showLoading() {
		layoutLoading.visibility = View.VISIBLE
		imgViewLoading.startAnimation(startRotateAnimation)
	}

	override fun hideLoading() {
		imgViewLoading.clearAnimation()
		layoutLoading.visibility = View.GONE
	}

	override fun onRetryButtonClicked() {
		homeScreenPresenter.retryLoadWeatherData()
	}

	override fun onDestroy() {
		homeScreenPresenter.onDestroy()
		super.onDestroy()
	}

	private fun initializeAnimationObjects() {
		startRotateAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_rotate)
		bottomUpAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_bottomsup)
	}

	private fun setCurrentWeatherData(weatherData: WeatherModel) {
		txtCurrentWeather.text = formatDegreeCelsiusData(weatherData.current.tempC)
		txtLocation.text = weatherData.location.locationName
	}

	private fun formatDegreeCelsiusData(tempC: Float?): String {
		val tempInt = (tempC!!).roundToInt()
		return String.format("%s%s", tempInt, "\u2103")
	}
}