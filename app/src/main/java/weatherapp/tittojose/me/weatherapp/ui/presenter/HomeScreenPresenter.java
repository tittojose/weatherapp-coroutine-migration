package weatherapp.tittojose.me.weatherapp.ui.presenter;

import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;
import weatherapp.tittojose.me.weatherapp.repository.WeatherRepositoryContract;
import weatherapp.tittojose.me.weatherapp.ui.view.HomeScreen;

public class HomeScreenPresenter implements HomePresenterContract {

    private final WeatherRepositoryContract.WeatherLoadListener weatherLoadListener;
    private HomeScreen homeScreen;
    private WeatherRepositoryContract weatherRepository;


    public HomeScreenPresenter(final HomeScreen homeScreen, WeatherRepositoryContract weatherRepository) {
        this.homeScreen = homeScreen;
        this.weatherRepository = weatherRepository;
        weatherLoadListener = new WeatherRepositoryContract.WeatherLoadListener() {
            @Override
            public void onWeatherDataLoadedSuccess(WeatherModel weatherModel) {
                homeScreen.hideLoading();
                homeScreen.onWeatherDataLoadSuccess(weatherModel);
            }

            @Override
            public void onWeatherDataLoadedFailed() {
                homeScreen.hideLoading();
                homeScreen.onWeatherDataLoadFailed();
            }
        };
        this.weatherRepository.setWeatherLoadListener(weatherLoadListener);
    }


    @Override
    public void loadWeatherData() {
        this.homeScreen.showLoading();
        this.weatherRepository.getWeatherData();
    }

    @Override
    public void retryLoadWeatherData() {
        loadWeatherData();
    }

}
