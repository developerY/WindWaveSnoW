package com.ylabz.windwatersnow.wind.ui

// NOTE: try to change to enum
sealed class WeatherEvent {
    data class SetLocation(val value: String) : WeatherEvent()
    data class StartCaptureSpeech2Txt(val updateText: (String) -> Unit, val finished: () -> Unit) : WeatherEvent()
    object DismissError : WeatherEvent()
}