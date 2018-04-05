package com.mobiquityinc.weather.ui.homescreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.domain.entities.FavouriteCity;

import java.util.List;

public class FavouriteCitiesAdapter extends RecyclerView.Adapter<CityItemView> {

    private List<FavouriteCity> cities;
    private CityItemClickedListener onCityClickListener;

    public FavouriteCitiesAdapter(List<FavouriteCity> cities, CityItemClickedListener onCityClickListener) {
        this.cities = cities;
        this.onCityClickListener = onCityClickListener;
    }

    @Override public CityItemView onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new CityItemView(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_city, parent, false), onCityClickListener);
    }

    @Override public void onBindViewHolder(final CityItemView holder, final int position) {
        final FavouriteCity city = cities.get(position);
        holder.bind(city);
    }

    @Override public int getItemCount() {
        return cities.size();
    }

    public void updateData(final List<FavouriteCity> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    public FavouriteCity getItemAtPosition(final int childAdapterPosition) {
        return cities.get(childAdapterPosition);
    }

    public void notifyItemChanged(final City city) {
        notifyItemChanged(cities.indexOf(city));
    }
}
