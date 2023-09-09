package kopring.abc.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionTranslator {
    @ExceptionHandler
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse> {
        return ResponseEntity.badRequest().body(ApiResponse(
            code = 400,
            message = e.message,
            data = e.fieldErrors.map { mapOf(it.field to it.defaultMessage) }))
    }
}
