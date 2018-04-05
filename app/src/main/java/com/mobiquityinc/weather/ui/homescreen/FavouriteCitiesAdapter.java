package com.mobiquityinc.weather.ui.homescreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.City;

import java.util.List;

public class FavouriteCitiesAdapter extends RecyclerView.Adapter<CityItemView> {

    private List<City> cities;
    private CityItemClickedListener onCityClickListener;

    public FavouriteCitiesAdapter(List<City> cities, CityItemClickedListener onCityClickListener) {
        this.cities = cities;
        this.onCityClickListener = onCityClickListener;
    }

    @Override public CityItemView onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new CityItemView(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_city, parent, false), onCityClickListener);
    }

    @Override public void onBindViewHolder(final CityItemView holder, final int position) {
        final City city = cities.get(position);
        holder.bind(city);
    }

    @Override public int getItemCount() {
        return cities.size();
    }

    public void updateData(final List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }
}
