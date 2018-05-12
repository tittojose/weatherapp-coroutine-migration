package weatherapp.tittojose.me.weatherapp.model.pojo;

import com.google.gson.annotations.SerializedName;

public class ForecastDay {
    @SerializedName("date")
    private String date;
    @SerializedName("date_epoch")
    private Integer dateEpoch;
    @SerializedName("day")
    private Day day;

    public String getDate() {
        return date;
    }

    public Integer getDateEpoch() {
        return dateEpoch;
    }

    public Day getDay() {
        return day;
    }
}
