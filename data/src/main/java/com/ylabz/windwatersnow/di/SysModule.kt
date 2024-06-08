package com.ylabz.windwatersnow.di

import android.content.Context
import com.ylabz.windwatersnow.data.model.AudioSystem
import com.ylabz.windwatersnow.data.model.CameraSystem
import com.ylabz.windwatersnow.data.model.DiskSystem
import com.ylabz.windwatersnow.data.model.MLSystem
import com.ylabz.windwatersnow.data.repository.AudioSysImpl
import com.ylabz.windwatersnow.data.repository.CameraSysImpl
import com.ylabz.windwatersnow.data.repository.DiskSysImpl
import com.ylabz.windwatersnow.data.repository.MLSysImpl
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

    @Provides
    @Singleton
    fun provideMLSystem(@ApplicationContext appContext: Context): MLSystem { //, pathToImage: String
        return MLSysImpl(appContext) //pathToImage
    }

    @Provides
    @Singleton
    fun provideCameraSystem(@ApplicationContext appContext: Context): CameraSystem {
        return CameraSysImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideDiskSystem(@ApplicationContext appContext: Context): DiskSystem {
        return DiskSysImpl(appContext)
    }

}