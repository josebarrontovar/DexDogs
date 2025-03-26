package com.example.dexdogs.data.model.responses

import com.squareup.moshi.Json

class DefaultResponse(
    val message: String,
    @Json(name = "is_success") val is_success: Boolean
)