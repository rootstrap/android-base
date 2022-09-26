package com.rootstrap.data.dto.response

import com.rootstrap.data.api.ApiException

sealed class DataResult<out R> {

    data class Success<out T>(val data: T?) : DataResult<T>()

    data class Error(val exception: ApiException) : DataResult<Nothing>()
}
