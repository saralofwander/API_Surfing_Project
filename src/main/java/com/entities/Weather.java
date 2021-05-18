package com.entities;

import io.swagger.annotations.ApiModelProperty;

public class Weather {

    @ApiModelProperty(notes = "Temperature")
    private double temperature;

    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
