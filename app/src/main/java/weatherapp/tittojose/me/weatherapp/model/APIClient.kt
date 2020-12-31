package weatherapp.tittojose.me.weatherapp.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
	private const val BASE_URL = "http://api.weatherstack.com/"
	private fun getClient(): Retrofit {
		val interceptor = HttpLoggingInterceptor()
		interceptor.level = HttpLoggingInterceptor.Level.BODY
		val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
//			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.client(client)
			.build()
	}

	val weatherAPIClient: WeatherAPI
		get() = getClient().create(WeatherAPI::class.java)
}