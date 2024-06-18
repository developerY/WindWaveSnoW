package com.ylabz.windwatersnow.wind.ui

// NOTE: try to change to enum
sealed class WeatherEvent {
    object GetWeather : WeatherEvent()
}