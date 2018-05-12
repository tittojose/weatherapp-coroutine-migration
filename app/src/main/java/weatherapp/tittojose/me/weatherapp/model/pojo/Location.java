package weatherapp.tittojose.me.weatherapp.model.pojo;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("name")
    String locationName;

    public String getLocationName() {
        return locationName;
    }
}
