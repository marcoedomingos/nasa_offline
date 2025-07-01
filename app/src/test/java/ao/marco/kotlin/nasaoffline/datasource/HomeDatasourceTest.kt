package ao.marco.kotlin.nasaoffline.datasource

import ao.marco.kotlin.nasaoffline.datasource.state.ImageSuccessState
import ao.marco.kotlin.nasaoffline.model.SuccessResponse
import ao.marco.kotlin.nasaoffline.provider.INetworkProvider
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HomeDatasourceTest {
    private lateinit var provider: INetworkProvider
    private lateinit var datasource: HomeDatasource

    @BeforeEach
    fun setup() {
        provider = mock()
        datasource = HomeDatasource(provider)
    }

    @Test
    fun `getImage returns an object as a successful response`() = runBlocking {
        val jsonBody = mapOf(
            "url" to "https://example.com/image.jpg",
            "hdurl" to "https://example.com/hd.jpg",
            "title" to "NASA Image",
            "date" to "2023-01-01",
            "explanation" to "Some explanation"
        )

        `when`(
            provider.get(
                path = "/planetary/apod",
                headers = mapOf(),
                query = mapOf("api_key" to "Your-API-KEY")
            )
        ).thenReturn(SuccessResponse(body = jsonBody))

        val result = datasource.getImage()
        assertTrue(result is ImageSuccessState)
        assertEquals("NASA Image", result.imageModel?.title)
    }

    @Test
    fun `getPhotos returns successful`() = runBlocking {
        val jsonBody = mapOf(
            "photos" to mutableListOf(
                mapOf(
                    "img_src" to "https://example.com/image.jpg",
                    "camera" to mapOf(
                        "full_name" to "Example"
                    ),
                    "earth_date" to "2023-01-01",
                )
            )
        )

        `when`(
            provider.get(
                path = "/mars-photos/api/v1/rovers/curiosity/photos",
                headers = mapOf(),
                query = mapOf("api_key" to "Your-API-KEY", "sol" to "1000")
            )
        ).thenReturn(SuccessResponse(body = jsonBody))

        val result = datasource.getPhotos()
        assertTrue(result is ImageSuccessState)
        assertEquals(1, result.photos.size)
    }
}