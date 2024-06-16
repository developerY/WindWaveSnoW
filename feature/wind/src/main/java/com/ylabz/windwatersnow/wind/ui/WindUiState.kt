package com.ylabz.windwatersnow.wind.ui


sealed interface WindUiState {
    object Loading : WindUiState
    object Error : WindUiState
    data class Success(
        val weather : String,
        val data: List<String> = emptyList(),
        val audioFiles: List<String> = emptyList(),
        val photoFiles: List<String> = emptyList()
    ) : WindUiState
    // Else it is a failure ???
}
