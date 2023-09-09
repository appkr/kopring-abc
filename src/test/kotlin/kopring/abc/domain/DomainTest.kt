package kopring.abc.domain

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DomainTest {

    val logger = KotlinLogging.logger {}

    @Test
    fun instantiate() {
        assertDoesNotThrow {
            val cut = Album.fixture()
            cut.addSong(Song.fixture())
            cut.addSong(Song.fixture(2L, "광화문 연가"))

            logger.info { cut }
        }
    }
}
