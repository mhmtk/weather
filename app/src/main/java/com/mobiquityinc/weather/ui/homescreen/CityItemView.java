package com.mobiquityinc.weather.ui.homescreen;

import android.view.View;
import android.widget.TextView;

import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.FavouriteCity;
import com.mobiquityinc.weather.ui.view.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityItemView extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.text_view_label)
    protected TextView labelTextView;
    private final CityItemClickedListener onClickListener;
    private FavouriteCity city;

    public CityItemView(final View itemView, final CityItemClickedListener onClickListener) {
        super(itemView);
        this.onClickListener = onClickListener;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void bind(final FavouriteCity city) {
        this.city = city;
        labelTextView.setText(city.getCityName());
    }

    @Override
    public void onClick(View view) {
        onClickListener.onCityClicked(city);
    }
}
