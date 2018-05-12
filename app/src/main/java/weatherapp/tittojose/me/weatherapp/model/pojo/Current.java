package weatherapp.tittojose.me.weatherapp.model.pojo;

import com.google.gson.annotations.SerializedName;

public class Current {


    @SerializedName("temp_c")
    private Float tempC;

    public Float getTempC() {
        return tempC;
    }
}
