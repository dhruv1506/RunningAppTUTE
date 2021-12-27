package com.androiddevs.runningappyt.di

import android.content.Context
import androidx.room.Room
import com.androiddevs.runningappyt.db.RunningDataBase
import com.androiddevs.runningappyt.other.Constants.RUNNING_DATABSE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRunningDatabase(@ApplicationContext app:Context
    )= Room.databaseBuilder(
        app,
        RunningDataBase::class.java,
        RUNNING_DATABSE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunningDao(db:RunningDataBase)=db.getRunDao()
}