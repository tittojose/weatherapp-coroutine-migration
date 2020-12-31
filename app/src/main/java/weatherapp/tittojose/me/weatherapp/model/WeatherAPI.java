package weatherapp.tittojose.me.weatherapp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;


public interface WeatherAPI {
    @GET("current?access_key=d96017976d32359af3e9394810025dcf&query=bengaluru")
    Call<WeatherModel> getWeatherForecast();

}
