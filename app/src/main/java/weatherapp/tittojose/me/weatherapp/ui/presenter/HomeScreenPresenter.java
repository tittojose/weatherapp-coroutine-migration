package weatherapp.tittojose.me.weatherapp.ui.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import weatherapp.tittojose.me.weatherapp.model.WeatherAPI;
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;
import weatherapp.tittojose.me.weatherapp.ui.view.HomeScreen;

public class HomeScreenPresenter {

    private HomeScreen homeScreen;
    private WeatherAPI weatherAPI;

    public HomeScreenPresenter(HomeScreen homeScreen, WeatherAPI weatherAPIClient) {
        this.homeScreen = homeScreen;
        this.weatherAPI = weatherAPIClient;
    }


    public void loadWeatherData() {
        this.homeScreen.showLoading();
        this.weatherAPI.getWeatherForecast().enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                homeScreen.hideLoading();
                if (response.isSuccessful()) {
                    homeScreen.onWeatherDataLoadSuccess(response.body());
                } else {
                    homeScreen.onWeatherDataLoadFailed();
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                homeScreen.hideLoading();
                homeScreen.onWeatherDataLoadFailed();
            }
        });
    }

    public void retryLoadWeatherData() {
        loadWeatherData();
    }
}
