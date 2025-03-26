package com.example.dexdogs.data.model.responses

import com.squareup.moshi.Json

class DogListApiResponse(
    val message: String,
    @Json(name = "is_success")
    val isSuccess: Boolean,
    val data: DogListResponse
)