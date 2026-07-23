package com.example.glucode_getitdone_to_do_list

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.glucode_getitdone_to_do_list.core.database.AppDatabase
import com.example.glucode_getitdone_to_do_list.tasks.data.local.Task
import com.example.glucode_getitdone_to_do_list.tasks.data.local.TaskDao
import org.junit.After
import org.junit.Test
import kotlinx.coroutines.test.runTest

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.java


//https://developer.android.com/training/data-storage/room/testing-db
@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var taskDao: TaskDao
    private lateinit var db: AppDatabase

    @Before
    fun setupDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // This is the magic line!
        //Stores database schema in RAM.
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        )
            // This is strictly for testing so we don't have to worry about background threads
            .allowMainThreadQueries()
            .build()

        // Extract the DAO so we can test it
        taskDao = db.taskDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeTaskAndReadInList() = runTest {
        val task = Task(id = 3, title = "test_Title", description = "test description", isComplete = false)
        taskDao.insertAll(task)

        val loadedTask = taskDao.loadAllByIds(3)
        assertEquals(task, loadedTask[0])
    }
}