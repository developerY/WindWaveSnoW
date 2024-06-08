package com.ylabz.windwatersnow.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface windwatersnowDao {
    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM windwatersnow_table ORDER BY date ASC")
    fun getAllwindwatersnows(): Flow<List<windwatersnowEntity>> // convert to Flow in the implementation.

    // Inserting a new windwatersnow
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: windwatersnowEntity)



    // Deleting a windwatersnow
    @Delete
    suspend fun delete(todo: windwatersnowEntity)

    // Deleting all windwatersnows
    @Query("DELETE FROM windwatersnow_table")
    suspend fun deleteAll()

    // Deleting a windwatersnow by its id
    @Query("DELETE FROM windwatersnow_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    // Finding a windwatersnow by its id
    @Query("SELECT * FROM windwatersnow_table WHERE id = :id")
    suspend fun findById(id: Int): windwatersnowEntity?

    // Finding a windwatersnow by its title
    @Query("SELECT * FROM windwatersnow_table WHERE title LIKE :title")
    suspend fun findByTitle(title: String): windwatersnowEntity?

    // Finding a windwatersnow by its description
    @Query("SELECT * FROM windwatersnow_table WHERE description LIKE :description")
    suspend fun findByDescription(description: String): windwatersnowEntity?

    // Finding a windwatersnow by its date
    @Query("SELECT * FROM windwatersnow_table WHERE date LIKE :date")
    suspend fun findByDate(date: String): windwatersnowEntity?



    /**
     * Updating only alarm switch
     * By order id
     */
    @Update(entity = windwatersnowEntity::class)
    suspend fun update(obj: windwatersnowUpdate)

    // Finding a windwatersnow by its photoTodoId
    @Query("SELECT * FROM windwatersnow_table WHERE id = :id")
    suspend fun findByPhotoTodoId(id: Int): windwatersnowEntity?


}

// @Query("DELETE FROM myTableName")
