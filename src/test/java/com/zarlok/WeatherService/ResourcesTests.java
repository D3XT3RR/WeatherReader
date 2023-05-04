package com.zarlok.WeatherService;


import com.zarlok.WeatherService.entity.WeatherReading;
import com.zarlok.WeatherService.repository.WeatherReadingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ResourcesTests {

    @Autowired
    WeatherReadingRepository weatherReadingRepository;

    @Test
    public void testCRD(){
        WeatherReading weatherReading = new WeatherReading(9.9f, 11.11f, LocalDateTime.now());
        int idFromDatabase = weatherReadingRepository.save(weatherReading).getId();

        Optional<WeatherReading> weatherReadingFromDatabase = weatherReadingRepository.findById(idFromDatabase);
        Assertions.assertFalse(weatherReadingFromDatabase.isEmpty());
        Assertions.assertEquals(weatherReadingFromDatabase.get().getWind(), weatherReading.getWind());

        weatherReadingRepository.delete(weatherReadingFromDatabase.get());
        Optional<WeatherReading> weatherReadingAfterDelete = weatherReadingRepository.findById(idFromDatabase);
        Assertions.assertTrue(weatherReadingAfterDelete.isEmpty());
    }

    @Test
    public void testGettingLastFiveReadings(){
        List<WeatherReading> weatherReadings = weatherReadingRepository.getLatestFive();
        Assertions.assertEquals(weatherReadings.size(),5);
    }



}
