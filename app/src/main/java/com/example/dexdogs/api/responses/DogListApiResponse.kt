package com.example.dexdogs.api.responses

import com.squareup.moshi.Json
import retrofit2.http.Field

class DogListApiResponse(
    val message: String,
    @Json(name = "is_success")
    val isSuccess: Boolean,
    val data: DogListResponse
)