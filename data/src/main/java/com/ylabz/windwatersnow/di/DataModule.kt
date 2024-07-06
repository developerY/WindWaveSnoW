package com.ylabz.windwatersnow.di

import android.content.Context
import androidx.room.Room
import com.ylabz.windwatersnow.data.repository.windwatersnowRepoImpl
import com.ylabz.windwatersnow.data.windwatersnowDB
import com.ylabz.windwatersnow.data.windwatersnowDao
import com.ylabz.windwatersnow.data.windwatersnowRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): windwatersnowDB {
        return Room.databaseBuilder(
            appContext,
            windwatersnowDB::class.java,
            windwatersnowDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providewindwatersnowDao(windwatersnowDB: windwatersnowDB): windwatersnowDao {
        return windwatersnowDB.windwatersnowDao
    }

    @Provides
    @Singleton
    fun providewindwatersnowRepository(windwatersnowDao: windwatersnowDao): windwatersnowRepo {
        return windwatersnowRepoImpl(windwatersnowDao)
    }




}


