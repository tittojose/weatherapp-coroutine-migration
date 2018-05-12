package weatherapp.tittojose.me.weatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import weatherapp.tittojose.me.weatherapp.model.APIClient;
import weatherapp.tittojose.me.weatherapp.model.pojo.WeatherModel;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIClient.getWeatherAPIClient().getWeatherForecast().enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {

            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {

            }
        });
    }
}
