package com.ylabz.windwatersnow.di

import android.content.Context
import com.ylabz.windwatersnow.network.model.AudioSystem
import com.ylabz.windwatersnow.network.repo.AudioSysImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// application scope
@Module
@InstallIn(SingletonComponent::class)
object SysModule {

    @Provides
    @Singleton
    fun provideAudioSystem(@ApplicationContext appContext: Context): AudioSystem {
        return AudioSysImpl(appContext)
    }

}