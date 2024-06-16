package com.ylabz.windwatersnow.network.model

interface OpenWeatherAPI {
    suspend fun getWeather(location: String): String
}