package com.mobiquityinc.weather.ui.cityscreen;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.FavouriteCityRepositoryImpl;
import com.mobiquityinc.weather.domain.entities.Forecast;
import com.mobiquityinc.weather.domain.entities.ForecastBlock;
import com.mobiquityinc.weather.network.JsonObjectMapper;
import com.mobiquityinc.weather.ui.view.RecyclerView;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityScreenFragment extends Fragment implements CityScreenContract.View{

    private static String POSITION = "position";

    private CityScreenContract.Presenter presenter;

    private CityScreenActionListener callback;
    private ForecastBlockAdapter forecastBlockAdapter;

    public interface CityScreenActionListener {

    }

    @BindView(R.id.recycler_view_forecast)
    protected RecyclerView forecastBlockRecyclerView;
    @BindView(R.id.progress_bar)
    protected View progressBar;

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
        forecastBlockRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastBlockRecyclerView.setItemAnimator(new DefaultItemAnimator());
        forecastBlockRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        forecastBlockRecyclerView.setHasFixedSize(true);
        forecastBlockRecyclerView.setEmptyView(progressBar);
        forecastBlockAdapter = new ForecastBlockAdapter(Collections.<ForecastBlock>emptyList());
        forecastBlockRecyclerView.setAdapter(forecastBlockAdapter);
    }

    @Override
    public void displayData(Forecast forecast) {
        forecastBlockAdapter.updateData(forecast.getList());
    }
}
