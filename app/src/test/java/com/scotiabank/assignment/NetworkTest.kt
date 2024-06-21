import com.example.gitpeek.network.data.MockWebServerResponses
import com.google.gson.GsonBuilder
import com.scotiabank.assignment.network.UserService
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import okio.IOException
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class NetworkTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private lateinit var userService: UserService

    private fun createUserService(baseUrl: HttpUrl): UserService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer MOCK_API_KEY")
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(UserService::class.java)
    }

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/octocat/")
        userService = createUserService(baseUrl)
    }

    @Test
    fun `fetch user returns user data`() = runBlocking {
        // Arrange
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockWebServerResponses.userObjectResponse)
        )

        // Act
        val response = userService.search("octocat")

        // Assert
        Assert.assertNotNull(response)
        Assert.assertEquals("octocat", response.login)
    }

    @Test
    fun `fetch repos returns list of repos`() = runBlocking {
        // Arrange
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockWebServerResponses.reposListResponse)
        )

        // Act
        val response = userService.getRepos("octocat")

        // Assert
        Assert.assertTrue(response.isNotEmpty())
    }

    @Test
    fun `handle network error gracefully`() = runBlocking {
        // Arrange
        mockWebServer.enqueue(MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AFTER_REQUEST))
        try {
            // Act
            val response = userService.search("octocat")
            // If no exception is thrown, fail the test
            Assert.fail("Expected an exception to be thrown")
        } catch (e: Exception) {
            // Assert
            // Check if the exception is a network-related exception
            Assert.assertTrue(e is IOException)
        }
    }
    @Test
    fun `handle 404 error`() = runBlocking {
        // Arrange
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        )

        try {
            // Act
            val response = userService.getRepos("nonexistentuser")
            // If no exception is thrown, fail the test
            Assert.fail("Expected an exception to be thrown")
        } catch (e: Exception) {

            // Assert
            Assert.assertTrue(e is HttpException && e.code() == HttpURLConnection.HTTP_NOT_FOUND)
        }
    }


    @Test
    fun `verify response time is within acceptable limits`() = runBlocking {
        // Arrange
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockWebServerResponses.reposListResponse)
        )

        val startTime = System.currentTimeMillis()

        // Act
        val response = userService.getRepos("octocat")

        val endTime = System.currentTimeMillis()
        val executionTime = endTime - startTime

        // Assert
        Assert.assertTrue(executionTime < 500) // Example threshold; adjust as necessary
    }

    @After
    fun reset(){
        mockWebServer.shutdown()
    }
}
