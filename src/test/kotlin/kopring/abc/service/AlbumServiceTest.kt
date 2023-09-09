package kopring.abc.service

import kopring.abc.domain.Album
import kopring.abc.repository.AlbumRepository
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.Optional

@SpringBootTest
class AlbumServiceTest {

    val logger = KotlinLogging.logger { }

    @MockBean
    lateinit var mockRepository: AlbumRepository

    @Autowired
    lateinit var cut: AlbumService

    @BeforeEach
    fun setup() {
        val fixture = Album.fixture()
        Mockito
            .`when`(mockRepository.save(Mockito.any(Album::class.java)))
            .thenReturn(fixture)
        Mockito
            .`when`(mockRepository.findById(Mockito.anyLong()))
            .thenReturn(Optional.ofNullable(fixture))
        Mockito
            .`when`(mockRepository.findAll())
            .thenReturn(listOf(fixture))
        Mockito
            .doNothing()
            .`when`(mockRepository)
            .delete(Mockito.any(Album::class.java))
    }

    @Test
    fun listAlbum() {
        assertEquals(1, cut.listAlbum().count())
    }

    @Test
    fun getAlbum() {
        assertNotNull(cut.getAlbum(1L))
    }

    @Test
    fun updateAlbum() {
        val updated = cut.updateAlbum(1L, Album.fixture(1L, "붉은 노을"))

        assertNotNull(updated)
        assertEquals("붉은 노을", updated?.title)
    }

    @Test
    fun deleteAlbum() {
        assertDoesNotThrow {
            cut.deleteAlbum(1L)
        }
    }
}
