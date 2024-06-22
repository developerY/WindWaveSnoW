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

) : WeatherRepo {


    override suspend fun getCurrentWeather(location: String):  WeatherResponse? {
        return fetchWeatherData(location)
    }

}