package weatherapp.tittojose.me.weatherapp.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import weatherapp.tittojose.me.weatherapp.R;
import weatherapp.tittojose.me.weatherapp.model.APIClient;
import weatherapp.tittojose.me.weatherapp.model.WeatherAPI;
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;
import weatherapp.tittojose.me.weatherapp.ui.presenter.HomeScreenPresenter;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreen {

    private WeatherAPI weatherAPIClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        weatherAPIClient = APIClient.getWeatherAPIClient();

        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(this, weatherAPIClient);
        homeScreenPresenter.loadWeatherData();
    }

    @Override
    public void onWeatherDataLoadSuccess(WeatherModel weatherModel) {

    }

    @Override
    public void onWeatherDataLoadFailed() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onRetryButtonClicked() {

    }
}
