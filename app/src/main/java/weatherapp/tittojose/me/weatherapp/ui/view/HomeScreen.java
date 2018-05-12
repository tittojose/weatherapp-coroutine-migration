package weatherapp.tittojose.me.weatherapp.ui.view;

import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;

public interface HomeScreen {
    void onWeatherDataLoadSuccess(WeatherModel weatherModel);

    void onWeatherDataLoadFailed();

    void showLoading();

    void hideLoading();

    void onRetryButtonClicked();
}
