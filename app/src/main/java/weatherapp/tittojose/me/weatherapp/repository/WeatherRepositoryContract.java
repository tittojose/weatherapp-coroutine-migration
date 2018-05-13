package weatherapp.tittojose.me.weatherapp.repository;

import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;

public interface WeatherRepositoryContract {
    interface WeatherLoadListener {
        void onWeatherDataLoadedSuccess(WeatherModel weatherModel);

        void onWeatherDataLoadedFailed();
    }

    void getWeatherData();

    void setWeatherLoadListener(WeatherLoadListener weatherLoadListener);
}
