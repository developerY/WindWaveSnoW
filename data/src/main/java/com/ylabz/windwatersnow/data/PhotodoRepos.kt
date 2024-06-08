package com.ylabz.windwatersnow.data

import com.ylabz.windwatersnow.data.mapper.windwatersnow
import kotlinx.coroutines.flow.Flow

interface windwatersnowRepo {
    // Not sure where to put these ...
    fun allGetwindwatersnows(): Flow<List<windwatersnow>> // NOTE: wrap in Flow<Resource<<>>>

    suspend fun insert(windwatersnow: windwatersnow)

    //suspend fun addTodoPhoto(windwatersnow: windwatersnow)
    suspend fun delete(windwatersnow: windwatersnow)
    suspend fun getwindwatersnowById(windwatersnowId: Int): windwatersnow? // NOTE: wrap in Flow<Resource<<>>>
    suspend fun deleteAll()
    //abstract fun insert(windwatersnow: windwatersnow)


}

class InvalidTodoPhotoException(message: String) : Exception(message)