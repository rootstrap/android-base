package com.rootstrap.data.api

import com.rootstrap.data.api.endpoints.UserApi
import retrofit2.Retrofit

class ApiProvider {
    fun getUserApiService(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}
