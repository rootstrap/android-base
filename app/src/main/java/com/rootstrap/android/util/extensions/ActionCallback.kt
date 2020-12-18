package com.rootstrap.android.util.extensions

import com.google.gson.Gson
import com.rootstrap.android.network.models.ErrorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class ActionCallback {

    companion object {

        suspend fun <T> call(apiCall: Call<T>): Result<Data<T>> =
            withContext(Dispatchers.IO) {
                val response = apiCall.execute()
                handleResponse(response)
            }

        private fun <T> handleResponse(response: Response<T>): Result<Data<T>> {
            if (response.isSuccessful) {
                return Result.success(
                    Data(response.body())
                )
            } else {
                try {
                    response.errorBody()?.let {
                        val apiError = Gson().fromJson(it.charStream(), ErrorModel::class.java)
                        return Result.failure(
                            ApiException(
                                errorMessage = apiError.error
                            )
                        )
                    }
                } catch (ignore: Exception) {
                }
            }

            return Result.failure(ApiException(errorType = ApiErrorType.unknownError))
        }
    }
}

class Data<T>(val value: T?)

class ApiException(
    private val errorMessage: String? = null,
    val errorType: ApiErrorType = ApiErrorType.apiError
) : java.lang.Exception() {
    override val message: String?
        get() = errorMessage
}

enum class ApiErrorType {
    apiError,
    unknownError
}
