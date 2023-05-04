package com.zarlok.WeatherService.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zarlok.WeatherService.entity.WeatherReading;
import com.zarlok.WeatherService.repository.WeatherReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class WeatherReadingService {

    WeatherReadingRepository weatherReadingRepository;

    @Autowired
    public WeatherReadingService(WeatherReadingRepository weatherReadingRepository){
        this.weatherReadingRepository = weatherReadingRepository;
    }

    public WeatherReading getCurrentWeather(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            URL openMeteo = new URL("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true");
            JsonNode current = objectMapper.readTree(openMeteo).get("current_weather");
            String temperature = current.get("temperature").asText();
            String wind = current.get("windspeed").asText();
            return weatherReadingRepository.saveAndFlush(new WeatherReading(Float.parseFloat(temperature), Float.parseFloat(wind), LocalDateTime.now()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<WeatherReading> getLatestFiveReadings(){
        return weatherReadingRepository.getLatestFive();
    }
}
