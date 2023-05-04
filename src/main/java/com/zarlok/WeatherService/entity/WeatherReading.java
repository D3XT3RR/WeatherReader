package com.zarlok.WeatherService.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "readings")
public class WeatherReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "temperature")
    private float temperature;

    @Column(name = "wind")
    private float wind;

    @Column(name = "date")
    private LocalDateTime date;

    public WeatherReading() {
    }

    public WeatherReading(float temperature, float wind, LocalDateTime date) {
        this.temperature = temperature;
        this.wind = wind;
        this.date = date;
    }

    public WeatherReading(int id, float temperature, float wind, LocalDateTime date) {
        this.id = id;
        this.temperature = temperature;
        this.wind = wind;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeatherReading{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", wind=" + wind +
                ", date=" + date +
                '}';
    }
}
