package weatherapp.tittojose.me.weatherapp.model.pojo;

import com.google.gson.annotations.SerializedName;

public class Forecastday {
    @SerializedName("date")
    private String date;
    @SerializedName("date_epoch")
    private Integer dateEpoch;
    @SerializedName("day")
    private Day day;
}
