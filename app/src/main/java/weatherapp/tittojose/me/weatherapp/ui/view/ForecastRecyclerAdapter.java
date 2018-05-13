package weatherapp.tittojose.me.weatherapp.ui.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import weatherapp.tittojose.me.weatherapp.R;
import weatherapp.tittojose.me.weatherapp.Utils;
import weatherapp.tittojose.me.weatherapp.model.pojo.Forecast;
import weatherapp.tittojose.me.weatherapp.model.pojo.ForecastDay;

class ForecastRecyclerAdapter extends RecyclerView.Adapter {
    private Forecast forecast;

    public ForecastRecyclerAdapter(Forecast forecast) {
        this.forecast = forecast;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View forecastView = inflater.inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(forecastView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ForecastDay forecastDay = forecast.getForecastDay().get(position);
        ForecastViewHolder viewHolder = (ForecastViewHolder) holder;
        viewHolder.forecastDay.setText(Utils.getDayOfWeekFromDateString(forecastDay.getDate()));
        viewHolder.forecastTemperature.setText(String.format("%s%s", forecastDay.getDay().getAvgtempC(), (char) 0x00B0));
    }

    @Override
    public int getItemCount() {
        return forecast.getForecastDay().size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtForecastDay)
        TextView forecastDay;

        @BindView(R.id.txtForecastTemperature)
        TextView forecastTemperature;

        public ForecastViewHolder(View forecastView) {
            super(forecastView);
            ButterKnife.bind(this, forecastView);
        }
    }
}
