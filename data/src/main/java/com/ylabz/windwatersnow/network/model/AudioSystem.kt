package com.ylabz.windwatersnow.network.model

import android.net.Uri

enum class AudioFunction { PLAY_READY, PLAY_STOP, PLAY, REC, REC_STOP, EMPTY }


interface AudioSystem {
    //fun audioCommand(command: AudioFunction)
    //fun getUri(): Uri?
    //fun resetUri()

    fun startSpeechToText(updateText: (String) -> Unit, finished: () -> Unit)

    //val UI_STATE_UPDATE: String get() = "no idea" // TODO do something with this
}
