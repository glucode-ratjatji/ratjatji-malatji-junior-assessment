package com.example.glucode_getitdone_to_do_list

import com.example.glucode_getitdone_to_do_list.tasks.data.ytWeatherViewModel.WeatherViewModel
import com.example.glucode_getitdone_to_do_list.tasks.data.ytrepository.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.example.glucode_getitdone_to_do_list.tasks.data.ytutils.Result

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private val mockRepository = mockk<WeatherRepository>()
    private lateinit var viewModel: WeatherViewModel

    // This test dispatcher runs coroutines immediately, which is perfect for testing StateFlow
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        // Replace the Android Main thread with our test dispatcher
        Dispatchers.setMain(testDispatcher)
        viewModel = WeatherViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        // Clean up after the test
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchWeather updates weatherData state on Success`() = runTest {
        // Arrange
        val mockData = getMockWeatherResponse()
        coEvery { mockRepository.getWeatherData("Pretoria") } returns Result.Success(mockData)

        // Act
        viewModel.fetchWeather("Pretoria")

        // Assert
        assertEquals(mockData, viewModel.weatherData.value)
        assertEquals(null, viewModel.error.value)
        assertEquals(false, viewModel.isLoading.value) // Should be false after finishing
    }

    @Test
    fun `fetchWeather updates error state on Error`() = runTest {
        // Arrange
        val exception = Exception("Server crashed")
        coEvery { mockRepository.getWeatherData("Pretoria") } returns Result.Error(exception)

        // Act
        viewModel.fetchWeather("Pretoria")

        // Assert
        assertEquals(null, viewModel.weatherData.value)
        assertEquals("Server crashed", viewModel.error.value)
        assertEquals(false, viewModel.isLoading.value)
    }
}