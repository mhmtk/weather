package com.mobiquityinc.weather.ui.homescreen;

import android.view.View;
import android.widget.TextView;

import com.mobiquityinc.weather.R;
import com.mobiquityinc.weather.domain.entities.City;
import com.mobiquityinc.weather.ui.view.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityItemView extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.text_view_label)
    protected TextView labelTextView;
    @BindView(R.id.delete_image_button)
    protected View deleteButton;
    private final CityItemClickedListener cityItemClickListener;
    private City city;

    public CityItemView(final View itemView, final CityItemClickedListener cityItemClickListener) {
        super(itemView);
        this.cityItemClickListener = cityItemClickListener;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    public void bind(final City city) {
        this.city = city;
        labelTextView.setText(city.getName());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == itemView.getId()) {
            cityItemClickListener.onCityClicked(city);
        }
        if (view.getId() == deleteButton.getId()) {
            cityItemClickListener.onCityDeleteClicked(city);
        }
    }
}
