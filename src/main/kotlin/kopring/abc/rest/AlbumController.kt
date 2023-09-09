package kopring.abc.rest

import jakarta.validation.Valid
import kopring.abc.domain.Album
import kopring.abc.service.AlbumService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AlbumController(
    private val service: AlbumService
) {
    @PostMapping("/albums")
    fun createAlbum(@Valid @RequestBody dto: Album): ResponseEntity<ApiResponse> {
        return service.createAlbum(dto)
            .let {
                return ResponseEntity.ok(ApiResponse(data = it))
            }
    }

    @GetMapping("/albums")
    fun listAlbums(): ResponseEntity<ApiResponse> {
        return service.listAlbum()
            .let {
                return ResponseEntity.ok(ApiResponse(data = it))
            }
    }

    @GetMapping("/albums/{albumId}")
    fun getAlbum(@PathVariable albumId: Long): ResponseEntity<ApiResponse> {
        return service.getAlbum(albumId)
            ?.let {
                return ResponseEntity.ok(ApiResponse(data = it))
            }
            ?: run {
                return ResponseEntity.badRequest().body(ApiResponse(code = 400, message = "엔티티를 찾을 수 없습니다"))
            }
    }

    @PutMapping("/albums/{albumId}")
    fun getAlbum(@PathVariable albumId: Long, @RequestBody dto: Album): ResponseEntity<ApiResponse> {
        return service.updateAlbum(albumId, dto)
            ?.let {
                return ResponseEntity.ok(ApiResponse(data = it))
            }
            ?: run {
                return ResponseEntity.badRequest().body(ApiResponse(code = 400, message = "엔티티를 찾을 수 없습니다"))
            }
    }

    @DeleteMapping("/albums/{albumId}")
    fun deleteAlbum(@PathVariable albumId: Long): ResponseEntity<Unit> {
        return service.deleteAlbum(albumId)
            .let {
                return ResponseEntity.noContent().build()
            }
    }
}
