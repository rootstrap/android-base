package com.rootstrap.android.network.models

import com.squareup.moshi.Json

data class ErrorModel(
    @Json(name = "errors") val errors: Any?,
    @Json(name = "error") val error: String?
)

data class ErrorModelSerializer(val error: ErrorModel)
