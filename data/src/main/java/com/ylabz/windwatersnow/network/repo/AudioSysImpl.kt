package com.ylabz.windwatersnow.network.repo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.ylabz.windwatersnow.network.model.AudioSystem

class AudioSysImpl(val appContext: Context) : AudioSystem {

    // Speech to Txt
    /**
     * This function starts speech recognition and updates the provided `updateText` function with the recognized text.
     *
     * @param ctx The Android context.
     * @param updateText A function that takes the recognized text as a String parameter and updates the UI.
     * @param finished A function that is called when the speech recognition is finished.
     */
    override fun startSpeechToText(updateText: (String) -> Unit, finished: () -> Unit) {
        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(appContext)
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM,
        )
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(bytes: ByteArray?) {}
            override fun onEndOfSpeech() {
                finished()
                // changing the color of your mic icon to
                // gray to indicate it is not listening or do something you want
            }

            override fun onError(i: Int) {}

            override fun onResults(bundle: Bundle) {
                val result = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (result != null) {
                    // attaching the output
                    // to our viewmodel
                    // Log.d(TAG, result[0])
                    updateText(result[0])
                }
            }

            override fun onPartialResults(bundle: Bundle) {}
            override fun onEvent(i: Int, bundle: Bundle?) {}

        })
        speechRecognizer.startListening(speechRecognizerIntent)
    }


    // Txt to Speech

}