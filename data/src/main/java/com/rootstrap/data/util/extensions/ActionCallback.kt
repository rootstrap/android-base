package com.rootstrap.data.util.extensions

import com.google.gson.Gson
import com.rootstrap.data.api.ApiErrorType
import com.rootstrap.data.api.ApiException
import com.rootstrap.data.dto.response.DataResult
import com.rootstrap.data.dto.response.ErrorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class ActionCallback {

    companion object {

        suspend fun <T> call(apiCall: Call<T>): DataResult<Data<T>> =
            withContext(Dispatchers.IO) {
                val response = apiCall.execute()
                handleResponse(response)
            }

        private fun <T> handleResponse(response: Response<T>): DataResult<Data<T>> {
            if (response.isSuccessful) {
                return DataResult.Success(
                    Data(response.body())
                )
            } else {
                try {
                    response.errorBody()?.let {
                        val apiError = Gson().fromJson(it.charStream(), ErrorModel::class.java)
                        return DataResult.Error(
                            ApiException(
                                errorMessage = apiError.error
                            )
                        )
                    }
                } catch (ignore: Exception) {
                }
            }

            return DataResult.Error(ApiException(errorType = ApiErrorType.GENERIC_IO_ERROR))
        }
    }
}

class Data<T>(val value: T?)
