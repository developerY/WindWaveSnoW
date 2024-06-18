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

