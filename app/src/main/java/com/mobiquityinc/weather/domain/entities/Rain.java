package com.mobiquityinc.weather.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

class Rain {

    @JsonProperty("3h")
    private double threeHours;

    public Rain() {
    }

    public double getThreeHours() {
        return threeHours;
    }

    public void setThreeHours(double threeHours) {
        this.threeHours = threeHours;
    }
}
