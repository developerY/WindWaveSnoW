package com.ylabz.windwatersnow.network.model

interface AudioSystem {
    fun startSpeechToText(updateText: (String) -> Unit, finished: () -> Unit)
}
