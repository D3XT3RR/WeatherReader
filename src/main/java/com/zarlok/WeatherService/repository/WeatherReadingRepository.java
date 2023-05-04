package com.zarlok.WeatherService.repository;


import com.zarlok.WeatherService.entity.WeatherReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherReadingRepository extends JpaRepository<WeatherReading, Integer> {
    @Query(value = "SELECT * from READINGS ORDER BY id desc LIMIT 5", nativeQuery = true)
    List<WeatherReading> getLatestFive();
}
