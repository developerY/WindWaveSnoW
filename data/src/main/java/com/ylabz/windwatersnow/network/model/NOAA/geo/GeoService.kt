package com.ylabz.windwatersnow.network.model.NOAA.geo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsGeocodingService {
    @GET("search")
    suspend fun getCoordinates(
        @Query("q") cityName: String
    ): List<MapsGeocodingResponse>

    companion object {
        private const val BASE_URL = "https://geocode.maps.co/"

        fun create(): MapsGeocodingService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MapsGeocodingService::class.java)
        }
    }
}