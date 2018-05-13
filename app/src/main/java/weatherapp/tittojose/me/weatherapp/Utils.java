package weatherapp.tittojose.me.weatherapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getDayOfWeekFromDateString(String dateString) {
        String result = dateString;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateString);
            SimpleDateFormat eeee = new SimpleDateFormat("EEEE");
            result = eeee.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
