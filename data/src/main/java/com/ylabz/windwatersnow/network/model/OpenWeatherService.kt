package com.ylabz.windwatersnow.network.model

import retrofit2.http.GET
import retrofit2.http.Query

// Old and remove
interface OpenWeatherService {
    @GET("data/2.5/weather")
    suspend fun getCurrentOpenWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"  // Use "metric" for Celsius
    ): OpenWeatherResponse
}