package weatherapp.tittojose.me.weatherapp.ui.presenter;

import org.junit.Assert;
import org.junit.Test;

import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;
import weatherapp.tittojose.me.weatherapp.repository.WeatherRepositoryContract;
import weatherapp.tittojose.me.weatherapp.ui.view.HomeScreen;

public class HomeScreenPresenterTest {

    @Test
    public void weatherDataLoadSuccess() {

        final MockView mockView = new MockView();
        WeatherRepositoryContract weatherRepository = new WeatherRepositoryContract() {
            WeatherLoadListener weatherLoadListener;

            @Override
            public void getWeatherData() {
                weatherLoadListener.onWeatherDataLoadedSuccess(new WeatherModel());
            }

            @Override
            public void setWeatherLoadListener(WeatherLoadListener weatherLoadListener) {
                this.weatherLoadListener = weatherLoadListener;
            }
        };

        HomePresenterContract homePresenterContract = new HomeScreenPresenter(mockView, weatherRepository);
        homePresenterContract.loadWeatherData();
        Assert.assertEquals(true, mockView.isWeatherDataLoaded);
    }


    @Test
    public void weatherDataLoadFailed() {

        MockView mockView = new MockView();
        WeatherRepositoryContract weatherRepository = new WeatherRepositoryContract() {
            WeatherLoadListener weatherLoadListener;

            @Override
            public void getWeatherData() {
                weatherLoadListener.onWeatherDataLoadedFailed();
            }

            @Override
            public void setWeatherLoadListener(WeatherLoadListener weatherLoadListener) {
                this.weatherLoadListener = weatherLoadListener;
            }
        };

        HomePresenterContract homePresenterContract = new HomeScreenPresenter(mockView, weatherRepository);
        homePresenterContract.loadWeatherData();
        Assert.assertEquals(true, mockView.isWeatherDataFailed);
    }

    @Test
    public void weatherDataLoadingProgressTest() {

        final MockView mockView = new MockView();
        WeatherRepositoryContract weatherRepository = new WeatherRepositoryContract() {
            WeatherLoadListener weatherLoadListener;

            @Override
            public void getWeatherData() {
                Assert.assertEquals(true, mockView.isLoadingShown);
                weatherLoadListener.onWeatherDataLoadedSuccess(new WeatherModel());
                Assert.assertEquals(false, mockView.isLoadingShown);
            }

            @Override
            public void setWeatherLoadListener(WeatherLoadListener weatherLoadListener) {
                this.weatherLoadListener = weatherLoadListener;
            }
        };

        HomePresenterContract homePresenterContract = new HomeScreenPresenter(mockView, weatherRepository);
        homePresenterContract.loadWeatherData();
    }

    class MockView implements HomeScreen {

        boolean isLoadingShown;
        boolean isWeatherDataLoaded;
        boolean isWeatherDataFailed;

        @Override
        public void onWeatherDataLoadSuccess(WeatherModel weatherModel) {
            isWeatherDataLoaded = true;
        }

        @Override
        public void onWeatherDataLoadFailed() {
            isWeatherDataFailed = true;
        }

        @Override
        public void showLoading() {
            isLoadingShown = true;
        }

        @Override
        public void hideLoading() {
            isLoadingShown = false;
        }

        @Override
        public void onRetryButtonClicked() {

        }
    }

}