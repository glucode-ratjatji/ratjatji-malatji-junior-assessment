import com.example.glucode_getitdone_to_do_list.getMockWeatherResponse
import com.example.glucode_getitdone_to_do_list.weather.api.WeatherApiService
import com.example.glucode_getitdone_to_do_list.weather.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response
import com.example.glucode_getitdone_to_do_list.weather.utils.Result

class WeatherRepositoryTest {

    // 1. Create a mock of the ApiService
    private val mockApiService = mockk<WeatherApiService>()

    // 2. Pass the mock into the Repository
    private val repository = WeatherRepository(mockApiService)

    @Test
    fun `getWeatherData returns Success when API responds successfully`() = runTest {
        // Arrange
        val mockData = getMockWeatherResponse()
        // Tell the mock service: "Whenever getCurrentWeather is called with ANY arguments, return this success response"
        coEvery {
            mockApiService.getCurrentWeather(any(), any())
        } returns Response.success(mockData)

        // Act
        val result = repository.getWeatherData("Pretoria")

        // Assert
        assertTrue(result is Result.Success)
        assertEquals(mockData.location.name, (result as Result.Success).data.location.name)
    }

    @Test
    fun `getWeatherData returns Error when API throws an exception`() = runTest {
        // Arrange
        coEvery {
            mockApiService.getCurrentWeather(any(), any())
        } throws RuntimeException("No internet connection")

        // Act
        val result = repository.getWeatherData("Pretoria")

        // Assert
        assertTrue(result is Result.Error)
        assertEquals("No internet connection", (result as Result.Error).exception.message)
    }
}