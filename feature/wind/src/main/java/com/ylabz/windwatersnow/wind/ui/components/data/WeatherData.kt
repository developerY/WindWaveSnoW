package com.ylabz.windwatersnow.wind.ui.components.data

import com.google.gson.annotations.SerializedName

data class Coord(@SerializedName("lon") val lon: Double, @SerializedName("lat") val lat: Double)
data class Weather(@SerializedName("main") val main: String, @SerializedName("description") val description: String)
data class Main(@SerializedName("temp") val temp: Double, @SerializedName("feels_like") val feelsLike: Double, @SerializedName("humidity") val humidity: Int)
data class Wind(@SerializedName("speed") val speed: Double, @SerializedName("deg") val deg: Int)
data class Waves(@SerializedName ("height") val height: Double)
data class Snow(@SerializedName ("volume") val volume: Double = 0.0)



val sampleWeatherData = """{
                "wind": { "speed": 5.5, "deg": 240 },
                "snow": { "volume": 30 },
                "waves": { "height": 5 },
                "main": { "temp": -5 },
                "name": "Open Sea"
            }""".trimMargin()




data class WindResponse(
    val wind: Wind,
    val name: String
)

data class SnowboardResponse(
    val wind: Wind,
    val snow: Snow,
    val main: Main,
    val name: String
)


data class SurfResponse(
    val wind: Wind,
    val waves: Waves,
    val name: String
)

