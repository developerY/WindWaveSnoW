package com.ylabz.windwatersnow.network.service.weather

import com.ylabz.windwatersnow.network.model.WeatherResponse
import com.ylabz.windwatersnow.data.BuildConfig.OPEN_WEATHER_API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

suspend fun fetchWeatherData(cityName: String) : WeatherResponse? {
    var call : WeatherResponse? = null

    try {
        call = RetrofitClient.weatherService.getCurrentWeather(cityName, OPEN_WEATHER_API_KEY)
    } catch (e: HttpException) {
        if (e.code() == 404) {
            print("City not found")
        } else {
            print("Error: ${e.message()}")
        }
        call = null
    } catch (e: Exception) {
       print("An unexpected error occurred")
        call = null
    }


    return call
}
