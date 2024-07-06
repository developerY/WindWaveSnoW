package com.ylabz.windwatersnow.network

import com.ylabz.windwatersnow.network.model.NOAA.NOAAWeatherDataResponse
import com.ylabz.windwatersnow.network.model.OpenWeatherResponse
import com.ylabz.windwatersnow.network.repo.WeatherRepo
import com.ylabz.windwatersnow.network.service.weather.FetchWeatherData
import com.ylabz.windwatersnow.network.service.weather.OpenFetchWeatherData
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(

) : WeatherRepo {


    // NOAA
    override suspend fun currentWeather(locationId: String, startDate: String, endDate: String):  NOAAWeatherDataResponse? {
        return FetchWeatherData(locationId, startDate, endDate)
    }


    // Old Remove
    override suspend fun openCurrentWeather(location: String):  OpenWeatherResponse? {
        return OpenFetchWeatherData(location)
    }



}