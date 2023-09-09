package kopring.abc.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import kopring.abc.domain.Album
import kopring.abc.repository.AlbumRepository
import kopring.abc.service.AlbumService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {

    companion object {
        val fixtures = listOf(Album.fixture(), Album.fixture(2L, "가로수 그늘 아래 서면"))
        val mapper = ObjectMapper()
        init {
            mapper.registerModules(JavaTimeModule())
        }
    }

    @MockBean
    lateinit var mockService: AlbumService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var repository: AlbumRepository

    @BeforeEach
    fun setup() {
        repository.saveAll(fixtures)
    }

    @AfterEach
    fun teardown() {
        repository.deleteAll()
    }

    @Test
    fun createAlbum() {
        val fixture = Album.fixture(3L, "광화문 연가")
        val json = mapper.writeValueAsString(fixture)
        Mockito
            .`when`(mockService.createAlbum(fixture))
            .thenReturn(fixture)

        mockMvc
            .perform(
                post("/api/albums")
                    .header("Content-type", "application/json")
                    .content(json)
            )
            .andExpect(status().isOk())
            .andDo(print())
    }

    @Test
    fun listAlbums() {
        Mockito
            .`when`(mockService.listAlbum())
            .thenReturn(fixtures)

        mockMvc
            .perform(get("/api/albums"))
            .andExpect(status().isOk())
            .andDo(print())
    }
}
