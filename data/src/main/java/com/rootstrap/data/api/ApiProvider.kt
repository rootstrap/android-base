package com.rootstrap.data.api

import com.rootstrap.data.api.endpoints.UserApi

class ApiProvider(private val apiServiceFactory: ApiServiceFactory) {
    fun getUserApiService(): UserApi {
        return apiServiceFactory.getApi(UserApi::class.java)
    }
}
