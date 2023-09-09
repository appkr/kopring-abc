package kopring.abc.repository

import kopring.abc.domain.Album
import org.springframework.data.jpa.repository.JpaRepository

interface AlbumRepository : JpaRepository<Album, Long>
