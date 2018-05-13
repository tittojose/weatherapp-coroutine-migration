package weatherapp.tittojose.me.weatherapp.repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import weatherapp.tittojose.me.weatherapp.model.WeatherAPI;
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;

public class WeatherRepository implements WeatherRepositoryContract {

    private final WeatherAPI weatherAPI;
    private WeatherLoadListener weatherLoadListener;

    public WeatherRepository(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    @Override
    public void setWeatherLoadListener(WeatherLoadListener weatherLoadListener) {
        this.weatherLoadListener = weatherLoadListener;
    }

    @Override
    public void getWeatherData() {
        this.weatherAPI.getWeatherForecast().enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                    weatherLoadListener.onWeatherDataLoadedSuccess(response.body());
                } else {
                    weatherLoadListener.onWeatherDataLoadedFailed();
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                weatherLoadListener.onWeatherDataLoadedFailed();
            }
        });
    }
}
