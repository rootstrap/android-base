package com.rootstrap.android.util.extensions

import com.google.gson.Gson
import com.rootstrap.android.network.models.ErrorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class ActionCallback {

    companion object {

        suspend fun <T> call(apiCall: Call<T>): Result<T> = withContext(Dispatchers.IO) {
            val call = async { apiCall.execute() }
            val response = call.await()
            manageResponse(response)
        }

        private fun <T> manageResponse(response: Response<T>): Result<T> {
            when {
                response.isSuccessful -> {
                    response.body()?.let {
                        return Result.success(it)
                    }
                }
                else -> {
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
            }

            return Result.failure(ApiException(errorType = ApiErrorType.uknownError))
        }
    }
}

class ApiException(
    private val errorMessage: String?,
    val errorType: ApiErrorType = ApiErrorType.apiError
) : java.lang.Exception() {
    override val message: String?
        get() = errorMessage
}

enum class ApiErrorType {
    apiError,
    uknownError
}
