package com.mobiquityinc.weather.ui.cityscreen;

import android.view.View;
import android.widget.TextView;

import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.ForecastBlock;
import com.mobiquityinc.weather.ui.view.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastItemView extends RecyclerView.ViewHolder {

    @BindView(R.id.date)
    protected TextView dateTextView;
    @BindView(R.id.temperature)
    protected TextView temperatureTextView;
    @BindView(R.id.humidity)
    protected TextView humidityTextView;
    @BindView(R.id.rain_chance)
    protected TextView rainChanceTextView;
    @BindView(R.id.wind)
    protected TextView windTextView;
    
    public ForecastItemView(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final ForecastBlock forecastBlock) {
        dateTextView.setText(forecastBlock.getDtTxt());
        if (forecastBlock.getMain() != null) {
            temperatureTextView.setText(
                    itemView.getContext().getString(R.string.temperature, forecastBlock.getMain().getTemp()));
            humidityTextView.setText(itemView.getContext().getString(R.string.humidity, forecastBlock.getMain().getHumidity()));
        }
        if (forecastBlock.getRain() != null) {
            rainChanceTextView.setText(
                    itemView.getContext().getString(R.string.rain_chance, forecastBlock.getRain().getThreeHours()));
        }
        if (forecastBlock.getWind() != null ) {
            windTextView.setText(itemView.getContext().getString(R.string.wind, forecastBlock.getWind().getSpeed(),
                    forecastBlock.getWind().getDeg()));
        }
    }
}
