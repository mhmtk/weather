package com.mobiquityinc.weather.ui.cityscreen;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.ForecastBlock;
import com.mobiquityinc.weather.ui.view.RecyclerView;

import java.util.List;

public class ForecastBlockAdapter extends RecyclerView.Adapter<ForecastItemView>{

    private List<ForecastBlock> forecastBlocks;

    public ForecastBlockAdapter(List<ForecastBlock> forecastBlocks) {
        this.forecastBlocks = forecastBlocks;
    }

    @Override public ForecastItemView onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ForecastItemView(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_forecast, parent, false));
    }

    @Override public void onBindViewHolder(final ForecastItemView holder, final int position) {
        holder.bind(forecastBlocks.get(position));
    }

    @Override public int getItemCount() {
        return forecastBlocks.size();
    }

    public void updateData(final List<ForecastBlock> forecastBlocks) {
        this.forecastBlocks = forecastBlocks;
        notifyDataSetChanged();
    }
}
