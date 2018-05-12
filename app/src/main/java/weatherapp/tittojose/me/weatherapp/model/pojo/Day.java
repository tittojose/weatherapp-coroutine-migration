package weatherapp.tittojose.me.weatherapp.model.pojo;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("avgtemp_c")
    private Float avgtempC;

    public Float getAvgtempC() {
        return avgtempC;
    }
}
