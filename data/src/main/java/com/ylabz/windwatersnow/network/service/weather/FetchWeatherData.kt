package com.ylabz.windwatersnow.network.service.weather

import com.ylabz.windwatersnow.network.model.WeatherResponse
import com.ylabz.windwatersnow.data.BuildConfig.OPEN_WEATHER_API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

suspend fun fetchWeatherData(cityName: String) : WeatherResponse {
    val call : WeatherResponse = RetrofitClient.weatherService.getCurrentWeather(cityName, OPEN_WEATHER_API_KEY)
    return call
}
