package com.ylabz.windwatersnow.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [windwatersnowEntity::class], version = 1, exportSchema = false)
abstract class windwatersnowDB : RoomDatabase() {

    abstract val windwatersnowDao: windwatersnowDao
    companion object {
        const val DATABASE_NAME = "windwatersnow_db"
        @JvmStatic
        fun getDatabase(context: Context): windwatersnowDB {
            return Room.databaseBuilder(
                context,
                windwatersnowDB::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}
