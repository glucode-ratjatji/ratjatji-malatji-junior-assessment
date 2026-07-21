package com.example.glucode_getitdone_to_do_list.core.di

import com.example.glucode_getitdone_to_do_list.tasks.data.repository.TaskRepositoryImpl
import com.example.glucode_getitdone_to_do_list.tasks.domain.repository.ITaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // @Binds is used instead of @Provides when you are mapping an interface to its implementation.
    // It tells Hilt: "Whenever someone asks for ITaskRepository, give them TaskRepositoryImpl."
    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        taskRepositoryImpl: TaskRepositoryImpl
    ): ITaskRepository
}