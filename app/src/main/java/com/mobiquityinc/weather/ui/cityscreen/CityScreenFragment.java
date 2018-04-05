package com.mobiquityinc.weather.ui.cityscreen;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.FavouriteCityRepositoryImpl;
import com.mobiquityinc.weather.domain.entities.Forecast;
import com.mobiquityinc.weather.domain.entities.ForecastBlock;
import com.mobiquityinc.weather.network.JsonObjectMapper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityScreenFragment extends Fragment implements CityScreenContract.View{

    private static String POSITION = "position";

    private CityScreenContract.Presenter presenter;

    private CityScreenActionListener callback;

    public interface CityScreenActionListener {

    }

    @BindView(R.id.temperature)
    protected TextView temperatureTextView;
    @BindView(R.id.humidity)
    protected TextView humidityTextView;
    @BindView(R.id.rain_chance)
    protected TextView rainChanceTextView;
    @BindView(R.id.wind)
    protected TextView windTextView;

    public static CityScreenFragment getInstance(LatLng position) {
        CityScreenFragment fragment = new CityScreenFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (CityScreenFragment.CityScreenActionListener) context;
        } catch (ClassCastException e) {

            throw new ClassCastException(context.toString()
                    + " must implement CityScreenActionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        ButterKnife.bind(this, view);
        JsonObjectMapper objectMapper = new JsonObjectMapper();
        presenter = new CityScreenPresenter(((LatLng) getArguments().getParcelable(POSITION)),
                objectMapper,
                new FavouriteCityRepositoryImpl(PreferenceManager.getDefaultSharedPreferences(getActivity()),
                        objectMapper),
                this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(CityScreenContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initiateUI() {

    }

    @Override
    public void displayData(Forecast forecast) {
        ForecastBlock forecastBlock = forecast.getList().get(0);
        if (forecastBlock.getMain() != null) {
            temperatureTextView.setText(
                    getString(R.string.temperature, forecastBlock.getMain().getTemp()));
        humidityTextView.setText(getString(R.string.humidity, forecastBlock.getMain().getHumidity()));
        }
        if (forecastBlock.getRain() != null) {
            rainChanceTextView.setText(
                    getString(R.string.rain_chance, forecastBlock.getRain().getThreeHours()));
        }
        if (forecastBlock.getWind() != null ) {
            windTextView.setText(getString(R.string.wind, forecastBlock.getWind().getSpeed(),
                    forecastBlock.getWind().getDeg()));
        }

    }
}
