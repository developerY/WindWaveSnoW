package com.ylabz.windwatersnow.network.repo

interface WeatherRepo {
    suspend fun getWeather(location: String): String
}