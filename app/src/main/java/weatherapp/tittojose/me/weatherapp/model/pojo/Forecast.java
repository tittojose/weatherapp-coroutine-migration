package weatherapp.tittojose.me.weatherapp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("forecastday")
    private List<ForecastDay> forecastDay = null;

    public List<ForecastDay> getForecastDay() {
        return forecastDay;
    }
}
