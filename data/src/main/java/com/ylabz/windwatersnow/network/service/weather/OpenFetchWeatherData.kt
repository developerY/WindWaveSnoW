package com.ylabz.windwatersnow.network.service.weather

import com.ylabz.windwatersnow.network.model.OpenWeatherResponse
import com.ylabz.windwatersnow.data.BuildConfig.OPEN_WEATHER_API_KEY
import com.ylabz.windwatersnow.network.model.OpenWeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

suspend fun OpenFetchWeatherData(cityName: String) : OpenWeatherResponse? {
    var call : OpenWeatherResponse? = null

    try {
        call = OpenRetrofitClient.openWeatherService.getCurrentOpenWeather(cityName, OPEN_WEATHER_API_KEY)
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

private object OpenRetrofitClient {
    private const val BASE_URL = "https://api.openweathermap.org/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val openWeatherService: OpenWeatherService = retrofit.create(OpenWeatherService::class.java)
}
