package com.ylabz.windwatersnow.network

import com.ylabz.windwatersnow.network.model.WeatherService
import com.ylabz.windwatersnow.network.repo.WeatherRepo
import com.ylabz.windwatersnow.network.model.WeatherResponse
import com.ylabz.windwatersnow.network.service.weather.fetchWeatherData
import retrofit2.Call
import javax.inject.Inject

import com.ylabz.windwatersnow.data.BuildConfig.OPEN_WEATHER_API_KEY
import com.ylabz.windwatersnow.network.model.MarineWeatherResponse
import com.ylabz.windwatersnow.network.model.MarineWeatherService

class WeatherRepoImpl @Inject constructor(
    // private val topicDao: TopicDao,
    private val network: WeatherService,
    private val marineWeatherService: MarineWeatherService

) : WeatherRepo {


    override suspend fun getCurrentWeather(location: String):  WeatherResponse? {
        return fetchWeatherData(location)
    }

    suspend fun getMarineWeather(latitude: Double, longitude: Double): MarineWeatherResponse? {
        return try {
            marineWeatherService.getMarineWeather(latitude, longitude, "YOUR_API_KEY", "metric")
        } catch (e: Exception) {
            null
        }
    }


}