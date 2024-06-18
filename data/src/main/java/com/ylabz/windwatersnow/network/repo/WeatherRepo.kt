package com.ylabz.windwatersnow.network.repo

import com.ylabz.windwatersnow.network.model.WeatherResponse

interface WeatherRepo {
    suspend fun getCurrentWeather(location: String): WeatherResponse?
}