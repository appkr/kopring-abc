package kopring.abc.repository

import kopring.abc.domain.Album
import kopring.abc.domain.Song
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class AlbumRepositoryTest {

    val logger = KotlinLogging.logger { }

    @Autowired
    lateinit var cut: AlbumRepository

    @Test
    @Transactional
    fun crud() {
        val saved = Album.fixture()
            .apply {
                addSong(Song.fixture())
                addSong(Song.fixture(2L, "광화문 연가"))
            }
            .run(cut::save)

        logger.info { saved }
    }
}
