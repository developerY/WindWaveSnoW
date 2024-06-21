package com.ylabz.windwatersnow.network.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"  // Use "metric" for Celsius
    ): WeatherResponse
}

interface MarineWeatherService {
    @GET("onecall")
    suspend fun getMarineWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("exclude") exclude: String = "minutely,hourly,daily,alerts"
    ): MarineWeatherResponse
}


