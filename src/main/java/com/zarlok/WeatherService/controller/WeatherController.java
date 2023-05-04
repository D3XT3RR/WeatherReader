package com.zarlok.WeatherService.controller;

import com.zarlok.WeatherService.service.WeatherReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    WeatherReadingService weatherReadingService;

    @Autowired
    public WeatherController(WeatherReadingService weatherReadingService){
        this.weatherReadingService = weatherReadingService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("current",weatherReadingService.getCurrentWeather());
        model.addAttribute("previous", weatherReadingService.getLatestFiveReadings());
        return "index";
    }
}
