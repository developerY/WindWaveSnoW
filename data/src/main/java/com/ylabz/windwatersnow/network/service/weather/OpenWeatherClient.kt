package com.ylabz.windwatersnow.network.service.weather

import com.ylabz.windwatersnow.data.BuildConfig.OPEN_WEATHER_API_KEY
import com.ylabz.windwatersnow.network.model.OpenWeatherAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class OpenWeatherClient : OpenWeatherAPI {


    override suspend fun getWeather(location: String): String {
        //api.openweathermap.org/data/2.5/weather?
        //api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=fc5f69d914fb3e578260b0c6efa8f618
        val base = "https://api.openweathermap.org/data/2.5/weather"
        val location_test = "London,uk"
        val call = "$base?q=$location_test&APPID=$OPEN_WEATHER_API_KEY"
        return withContext(Dispatchers.IO) {
            URL(call).readText()
        }
    }


}