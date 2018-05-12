package weatherapp.tittojose.me.weatherapp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;


public interface WeatherAPI {
    @GET("forecast.json?key=c9f43dcc3cde41dcb6e124416181205&q=bengaluru&days=4")
    Call<WeatherModel> getWeatherForecast();

}
