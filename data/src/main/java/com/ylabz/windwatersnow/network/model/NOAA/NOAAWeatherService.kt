package com.ylabz.windwatersnow.network.model.NOAA

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NOAAMarineWeatherService {
    @Headers("User-Agent: YourAppName")
    @GET("gridpoints/{office}/{gridX},{gridY}/forecast")
    suspend fun getNOAAMarineWeather(
        @Path("office") office: String,
        @Path("gridX") gridX: Int,
        @Path("gridY") gridY: Int
    ): NOAAMarineWeatherResponse
}


/**
 * val response = noaaService.getWeatherData(
 *                     datasetId = "GHCND",
 *                     dataTypeId = "TMAX",
 *                     locationId = locationId,
 *                     startDate = startDate,
 *                     endDate = endDate,
 *                     units = "standard",
 *                     limit = 100,
 *                     apiKey = apiKey
 */
interface NOAAWeatherService {
    @Headers("User-Agent: YourAppName")
    @GET("data")
    suspend fun getWeatherData(
        @Query("datasetid") datasetId: String,
        @Query("datatypeid") dataTypeId: String,
        @Query("locationid") locationId: String,
        @Query("startdate") startDate: String,
        @Query("enddate") endDate: String,
        @Query("units") units: String,
        @Query("limit") limit: Int,
        @Query("apikey") apiKey: String
    ): NOAAWeatherDataResponse
}
