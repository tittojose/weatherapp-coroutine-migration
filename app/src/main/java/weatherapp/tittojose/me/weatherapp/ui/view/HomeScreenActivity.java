package weatherapp.tittojose.me.weatherapp.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import weatherapp.tittojose.me.weatherapp.R;
import weatherapp.tittojose.me.weatherapp.Utils;
import weatherapp.tittojose.me.weatherapp.model.APIClient;
import weatherapp.tittojose.me.weatherapp.model.WeatherAPI;
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;
import weatherapp.tittojose.me.weatherapp.repository.WeatherRepository;
import weatherapp.tittojose.me.weatherapp.repository.WeatherRepositoryContract;
import weatherapp.tittojose.me.weatherapp.ui.presenter.HomeScreenPresenter;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreen {

    private WeatherAPI weatherAPIClient;

    @BindView(R.id.txtCurrentWeather)
    TextView currentTemperatureTextView;

    @BindView(R.id.rvForecast)
    RecyclerView forecastRecyclerview;

    @BindView(R.id.txtLocation)
    TextView locationTextView;

    @BindView(R.id.btnRetry)
    Button retryButton;

    @BindView(R.id.imgViewLoading)
    ImageView loadingImageView;

    @BindView(R.id.layoutLoading)
    ViewGroup loadingLayout;

    @BindView(R.id.layoutWeather)
    ConstraintLayout weatherLayout;

    @BindView(R.id.layoutError)
    ViewGroup errorLayout;
    private Animation startRotateAnimation;
    private HomeScreenPresenter homeScreenPresenter;
    private Animation bottomUpAnimation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        initializeAnimationObjects();

        weatherAPIClient = APIClient.getWeatherAPIClient();

        WeatherRepositoryContract weatherRepositoryContract = new WeatherRepository(weatherAPIClient);

        homeScreenPresenter = new HomeScreenPresenter(this, weatherRepositoryContract);
        homeScreenPresenter.loadWeatherData();
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRetryButtonClicked();
            }
        });

    }

    private void initializeAnimationObjects() {
        startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_rotate);
        bottomUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_bottomsup);
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
        loadingImageView.startAnimation(startRotateAnimation);
    }

    @Override
    public void hideLoading() {
        loadingImageView.clearAnimation();
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void onRetryButtonClicked() {
        homeScreenPresenter.retryLoadWeatherData();
    }


    private void setForecastWeatherData(WeatherModel weatherModel) {
        forecastRecyclerview.setAdapter(new ForecastRecyclerAdapter(weatherModel.getForecast()));
        forecastRecyclerview.setAnimation(bottomUpAnimation);
    }

    private void setCurrentWeatherData(WeatherModel weatherData) {
        currentTemperatureTextView.setText(Utils.getDegreeCelsiusData(weatherData.getCurrent().getTempC()));
        locationTextView.setText(weatherData.getLocation().getLocationName());
    }
}