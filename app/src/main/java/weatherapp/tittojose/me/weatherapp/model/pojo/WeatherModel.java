package weatherapp.tittojose.me.weatherapp.model.pojo;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {

    @SerializedName("current")
    private Current current;

    @SerializedName("forecast")
    private Forecast forecast;
}
