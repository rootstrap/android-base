package com.rootstrap.android.infraestructure.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * This is example service class
 * */
interface ServiceProfile {

    @GET(USER_GET_PROFILE)
    fun getUserProfile(@Path(USER_PARAM_ID, encoded = true) userId: String): Call<Any>

}
