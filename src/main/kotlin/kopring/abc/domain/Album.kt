package kopring.abc.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@Entity
@Table(name = "albums")
class Album(
    var title: String? = null,
    @OneToMany(mappedBy = "album", cascade = [CascadeType.ALL], orphanRemoval = true)
    var songs: MutableList<Song> = mutableListOf(),
    @CreatedBy
    var createdBy: String? = null,
    @LastModifiedBy
    var updatedBy: String? = null,
    @CreatedDate
    var createdAt: Instant = Instant.now(),
    @LastModifiedDate
    var updatedAt: Instant = Instant.now(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 1L

    fun addSong(song: Song) {
        this.songs.add(song)
        this.songs.forEach {
            it.album = this
        }
    }

    companion object {
        fun fixture(id: Long = 1L, title: String = "이문세5집"): Album {
            return Album(title)
                .apply {
                    this.id = id
                    this.createdBy = "UNKNOWN"
                    this.updatedBy = "UNKNOWN"
                }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Album

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Album(id=$id, title=$title, songs=$songs, createdBy=$createdBy, updatedBy=$updatedBy, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
