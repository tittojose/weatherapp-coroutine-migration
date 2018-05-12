package weatherapp.tittojose.me.weatherapp.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import weatherapp.tittojose.me.weatherapp.R;
import weatherapp.tittojose.me.weatherapp.model.APIClient;
import weatherapp.tittojose.me.weatherapp.model.WeatherAPI;
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;
import weatherapp.tittojose.me.weatherapp.ui.presenter.HomeScreenPresenter;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreen {

    private WeatherAPI weatherAPIClient;

    @BindView(R.id.txtCurrentWeather)
    TextView currentTemperatureTextView;

    @BindView(R.id.rvForecast)
    RecyclerView forecastRecyclerview;

    @BindView(R.id.txtLocation)
    TextView locationTextView;

    @BindView(R.id.layoutLoading)
    ViewGroup loadingLayout;

    @BindView(R.id.layoutWeather)
    ViewGroup weatherLayout;

    @BindView(R.id.layoutError)
    ViewGroup errorLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        weatherAPIClient = APIClient.getWeatherAPIClient();

        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(this, weatherAPIClient);
        homeScreenPresenter.loadWeatherData();
    }

    @Override
    public void onWeatherDataLoadSuccess(WeatherModel weatherModel) {
        weatherLayout.setVisibility(View.VISIBLE);
        setCurrentWeatherData(weatherModel);
        setForecastWeatherData(weatherModel);
    }


    @Override
    public void onWeatherDataLoadFailed() {
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void onRetryButtonClicked() {

    }


    private void setForecastWeatherData(WeatherModel weatherModel) {
        forecastRecyclerview.setAdapter(new ForecastRecyclerAdapter(weatherModel.getForecast()));
    }

    private void setCurrentWeatherData(WeatherModel weatherData) {
        currentTemperatureTextView.setText(String.format("%s%s", weatherData.getCurrent().getTempC(), (char) 0x00B0));
        locationTextView.setText(weatherData.getLocation().getLocationName());
    }

}
