package com.example.weatherjson;

public class WeatherInfo {
    private String id;
    private String temp;
    private String weather;
    private String name;
    private String pm;
    private String wind;

    public String getId() {
        return id;
    }

    public String getTemp() {
        return temp;
    }

    public String getWeather() {
        return weather;
    }

    public String getName() {
        return name;
    }

    public String getPm() {
        return pm;
    }

    public String getWind() {
        return wind;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
