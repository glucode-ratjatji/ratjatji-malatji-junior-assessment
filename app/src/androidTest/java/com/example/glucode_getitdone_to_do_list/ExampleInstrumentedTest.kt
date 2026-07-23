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
    fun creatingTaskPass() = runTest {
        val task = Task(id = 3, title = "test_Title", description = "test description", isComplete = false)
        taskDao.insertAll(task)
        val loadedTask = taskDao.loadAllByIds(3)
        assertEquals(task, loadedTask[0])
    }

    @Test
    @Throws(Exception::class)
    fun deleteTaskPass() = runTest {
        val task= Task(id = 3, title = "test_Title", description = "test description", isComplete = false)
        taskDao.insertAll(task)
        taskDao.delete(task)

        val loadedTask = taskDao.loadAllByIds(3)
        assertTrue(loadedTask.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun deleteNonExistentTask_leavesDatabaseUnchanged() = runTest {
        //Insert a REAL task into the database
        val realTask = Task(id = 1, title = "Real Task", description = "I exist", isComplete = false)
        taskDao.insertAll(realTask)

        // Create a GHOST task that we will NEVER insert
        val ghostTask = Task(id = 99, title = "Ghost Task", description = "I do not exist", isComplete = false)

        // 2. Act: Attempt to delete the ghost task
        taskDao.delete(ghostTask)

        // 3. Assert: Verify the real task is completely unharmed and still in the database
        val loadedTasks = taskDao.loadAllByIds(1)

        // Check that we still have exactly 1 task, and it's the real one
        assertEquals(1, loadedTasks.size)
        assertEquals(realTask, loadedTasks[0])
}
}