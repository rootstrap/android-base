package com.rootstrap.android.infraestructure.connection

import com.rootstrap.android.utils.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Amaury Ricardo Miranda 2019
 * */

open class OnlineConnection<T> (private val call: Call<T>) {

    open fun catch(expected: ConnectionResponse<T>) = call.enqueue(
        ResponseCallBack(
            expected
        )
    )

    inner class ResponseCallBack<T>(private val expected: ConnectionResponse<T>) : Callback<T> {

        override fun onFailure(call: Call<T>, t: Throwable) = onError(ResponseError.NETWORK_ERROR.code)

        override fun onResponse(call: Call<T>, response: Response<T>) =
            if (response.isSuccessful) expected.isSuccessful(response.body()) else onError(response.code())

        /**
         * @param code ResponseError Code
         * */
        private fun onError(code: Int) {

            ioThread.execute{
                when (code) {
                    ResponseError.NETWORK_ERROR.code -> {

                    }
                    ResponseError.E_404.code -> {
                        //TODO() not found error
                    }
                    ResponseError.E_401.code -> {
                        //TODO() unauthorized error
                    }
                    else -> {
                        //TODO() any other error
                    }
                }
            }

            expected.onError()

        }
    }
}

enum class ResponseError(val code: Int) {
    NETWORK_ERROR(-1),
    E_404(404),
    E_401(404),
    E_500(404),
    E_400(404),
    E_300(404)
    //add more ResponseError
}
