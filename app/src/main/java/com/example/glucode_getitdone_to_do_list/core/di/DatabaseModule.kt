package com.example.glucode_getitdone_to_do_list.core.di

import android.content.Context
import androidx.room.Room
import com.example.glucode_getitdone_to_do_list.core.database.AppDatabase
import com.example.glucode_getitdone_to_do_list.tasks.data.local.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) //The only instance exists in the entire app
object DatabaseModule {

    // 1. Here is where your databaseBuilder goes
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "task_database"
        ).build()
    }

    // 2. Here is where you get the DAO instance
    @Provides
    @Singleton
    fun provideTaskDao(database: AppDatabase): TaskDao {
        return database.taskDao() // This replaces your `val userDao = db.userDao()`
    }
}