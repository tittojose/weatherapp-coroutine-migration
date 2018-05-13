package weatherapp.tittojose.me.weatherapp.ui.presenter;

import weatherapp.tittojose.me.weatherapp.model.WeatherAPI;
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

//        this.homeScreen.showLoading();
//
//
//        this.weatherAPI.getWeatherForecast().enqueue(new Callback<WeatherModel>() {
//            @Override
//            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
//                homeScreen.hideLoading();
//                if (response.isSuccessful()) {
//                    homeScreen.onWeatherDataLoadSuccess(response.body());
//                } else {
//                    homeScreen.onWeatherDataLoadFailed();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<WeatherModel> call, Throwable t) {
//                homeScreen.hideLoading();
//                homeScreen.onWeatherDataLoadFailed();
//            }
//        });

//        this.homeScreen.showLoading();
//        getObservable().subscribeWith(getObserver());
    }

    @Override
    public void retryLoadWeatherData() {
        loadWeatherData();
    }

}
