package kopring.abc.service

import kopring.abc.domain.Album
import kopring.abc.repository.AlbumRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AlbumService(
    private val repository: AlbumRepository
) {
    @Transactional
    fun createAlbum(dto: Album): Album {
        return repository.save(dto);
    }

    @Transactional(readOnly = true)
    fun listAlbum(): List<Album> {
        return repository.findAll()
    }

    @Transactional(readOnly = true)
    fun getAlbum(albumId: Long): Album? {
        return repository.findByIdOrNull(albumId)
    }

    @Transactional
    fun updateAlbum(albumId: Long, dto: Album): Album? {
        return repository.findByIdOrNull(albumId)
            ?.let {
                if (dto.title != it.title) {
                    it.title = dto.title
                }
                return repository.save(it)
            }
    }

    @Transactional
    fun deleteAlbum(albumId: Long) {
        repository.findByIdOrNull(albumId)
            ?.let(repository::delete)
    }
}
