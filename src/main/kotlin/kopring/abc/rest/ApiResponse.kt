package kopring.abc.rest

data class ApiResponse(
    val code: Int = 200,
    val message: String = "성공",
    val data: Any? = null,
)
