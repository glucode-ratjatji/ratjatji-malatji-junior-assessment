package com.example.glucode_getitdone_to_do_list.LocationConstants

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

object LocationConstants {
    const val LOCATION_UPDATE_INTERVAL = 10_000L // 10 seconds
}

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    @Named("location_interval")
    fun provideLocationUpdateInterval(): Long = LocationConstants.LOCATION_UPDATE_INTERVAL
}