package com.ylabz.windwatersnow.network.service.weather

import com.ylabz.windwatersnow.data.BuildConfig.NOAA_WEATHER_API_KEY
import com.ylabz.windwatersnow.network.model.NOAA.NOAAMarineWeatherService
import com.ylabz.windwatersnow.network.model.NOAA.NOAAWeatherDataResponse
import com.ylabz.windwatersnow.network.model.NOAA.NOAAWeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

suspend fun FetchWeatherData(locationId: String, startDate: String, endDate: String) : NOAAWeatherDataResponse? {
    var call : NOAAWeatherDataResponse? = null

    try {
        call = NOAAWeatherRetrofitClient.noaaWeatherService.getWeatherData("", "",
            locationId, startDate, endDate,"metric", 4 , NOAA_WEATHER_API_KEY
        )
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

object NOAAWeatherRetrofitClient{
    private const val BASE_URL = "https://www.ncdc.noaa.gov/cdo-web/api/v2/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val noaaWeatherService: NOAAWeatherService = retrofit.create(
        NOAAWeatherService::class.java)
}

object NOAAMarineRetrofitClient {
    private const val BASE_URL = "https://api.weather.gov/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val noaaMarineWeatherService: NOAAMarineWeatherService = retrofit.create(
        NOAAMarineWeatherService::class.java)
}
