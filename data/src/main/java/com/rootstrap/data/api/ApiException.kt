package com.rootstrap.data.api

class ApiException(
    private val errorMessage: String? = null,
    val errorType: ApiErrorType = ApiErrorType.API_ERROR,
) : Exception() {
    override val message: String?
        get() = errorMessage

    override fun toString(): String {
        return errorType.name + " - " + message
    }
}

enum class ApiErrorType {
    API_ERROR,
    SOCKET_TIME_OUT,
    GENERIC_IO_ERROR,
}
