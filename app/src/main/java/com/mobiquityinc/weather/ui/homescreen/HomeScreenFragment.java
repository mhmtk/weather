package com.mobiquityinc.weather.ui.homescreen;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.ui.view.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeScreenFragment extends Fragment implements HomeScreenContract.View, CityItemClickedListener {

    private HomeScreenActionListener callback;

    public interface HomeScreenActionListener {
        void onCitySelected(final City city);
        void onAddClicked();
    }

    private HomeScreenContract.Presenter presenter;
    private FavouriteCitiesAdapter favouriteCitiesAdapter;
    @BindView(R.id.recycler_view_favourites)
    protected RecyclerView favouriteCitiesRecyclerView;
    @BindView(R.id.text_view_empty)
    protected TextView emptyTextView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (HomeScreenActionListener) activity;
        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement HomeScreenActionListener");
        }
    }

    @Override
    public void setPresenter(HomeScreenContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void initiateUI() {
        favouriteCitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favouriteCitiesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        favouriteCitiesRecyclerView.setHasFixedSize(true);
        favouriteCitiesRecyclerView.setEmptyView(emptyTextView);
        favouriteCitiesAdapter = new FavouriteCitiesAdapter(Collections.<City>emptyList(), this);
        favouriteCitiesRecyclerView.setAdapter(favouriteCitiesAdapter);
    }

    @Override
    public void startMapFragment() {
        callback.onAddClicked();
    }

    @Override
    public void launchCityScreen(City city) {
        callback.onCitySelected(city);
    }

    @Override
    public void setFavouriteCities(Set<City> favourites) {
        favouriteCitiesAdapter.updateData(new ArrayList<>(favourites));
    }

    @OnClick(R.id.fab_add_city)
    public void addCityButtonClicked() {
        presenter.addCityButtonClicked();
    }

    @Override
    public void onCityClicked(City city) {
        presenter.onCityClicked(city);
    }
}
