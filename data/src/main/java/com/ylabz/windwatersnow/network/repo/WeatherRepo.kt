package com.ylabz.windwatersnow.network.repo

import com.ylabz.windwatersnow.network.model.NOAA.NOAAWeatherDataResponse
import com.ylabz.windwatersnow.network.model.OpenWeatherResponse

interface WeatherRepo {
    suspend fun currentWeather(locationId: String, startDate: String, endDate: String):  NOAAWeatherDataResponse?

     // Old Remove
    suspend fun openCurrentWeather(location: String): OpenWeatherResponse?

}