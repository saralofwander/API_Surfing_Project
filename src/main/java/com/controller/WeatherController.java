package com.controller;

import com.entities.Weather;
import com.services.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather/")
public class WeatherController {

    @Autowired
    private WeatherService weatherservice;


    @GetMapping("/{location}")
    @ApiOperation(
            value = "Get weather by city",
            notes = "Get weather by city",
            response = Weather.class)
    public Weather getByCity(@ApiParam(value = "city", required = true) @PathVariable String location) {
        return weatherservice.getWeather(location);
    }

}











