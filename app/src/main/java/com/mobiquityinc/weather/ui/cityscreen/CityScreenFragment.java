package com.mobiquityinc.weather.ui.cityscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.network.JsonObjectMapper;

import butterknife.ButterKnife;

public class CityScreenFragment extends Fragment implements CityScreenContract.View{

    private static String POSITION = "position";

    private CityScreenContract.Presenter presenter;

    private CityScreenActionListener callback;

    public interface CityScreenActionListener {

    }

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
        presenter = new CityScreenPresenter(((LatLng) getArguments().getParcelable(POSITION)),
                new JsonObjectMapper(),
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
}
