package com.llanezaweatherapp.cerealboxcreations.llanezaweatherapp;


public class WeatherInfo {

    //This is the Class that will hold the data gathered from the weather API

    String location;
    String weather;
    String weatherDescription;
    String temp;
    String windSpeed;
    String humidity;
    String weatherIcon;

    public WeatherInfo(String location, String weather, String weatherDescription, String temp, String windSpeed, String humidity, String weatherIcon){
        this.location = location;
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.weatherIcon = weatherIcon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
