package com.ylabz.windwatersnow.data.repository

import androidx.annotation.WorkerThread
import com.ylabz.windwatersnow.data.mapper.toPhoto
import com.ylabz.windwatersnow.data.mapper.towindwatersnowEntity
import com.ylabz.windwatersnow.data.mapper.windwatersnow
import com.ylabz.windwatersnow.data.windwatersnowDao
import com.ylabz.windwatersnow.data.windwatersnowRepo
import kotlinx.coroutines.flow.Flow


class windwatersnowRepoImpl( // windwatersnowRepositoryImpl
    private val windwatersnowDao: windwatersnowDao,
) : windwatersnowRepo {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    /**
     * this is where the conversion happens
     * Type mismatch.
     * Required: Flow<List<windwatersnow>>
     *     Found:
     *     Flow<List<windwatersnowEntity>>
     */
    @WorkerThread
    override fun allGetwindwatersnows(): Flow<List<windwatersnow>> {
        return windwatersnowDao.getAllwindwatersnows()
        /*return flow {
            val td = todoDao.getTask().map { it.toPhoto() }
            if(td.isNotEmpty())
                Log.d(TAG, "this is the last in flow in Repo Imp ${td.last().title}")
            emit(td)
        }*/
    }


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    override suspend fun insert(todo: windwatersnow) {
        val photoEnt = todo.towindwatersnowEntity()
        // Log.d(, "This is in todoDao --  ${photoEnt}")
        windwatersnowDao.insert(photoEnt)
    }

    @WorkerThread
    override suspend fun delete(todo: windwatersnow) {
        windwatersnowDao.delete(todo.towindwatersnowEntity())
    }

    @WorkerThread
    override suspend fun getwindwatersnowById(windwatersnowId: Int): windwatersnow? {
        return windwatersnowDao.findByPhotoTodoId(windwatersnowId)?.toPhoto()
    }

    @WorkerThread
    override suspend fun deleteAll() {
        windwatersnowDao.deleteAll()
    }

}