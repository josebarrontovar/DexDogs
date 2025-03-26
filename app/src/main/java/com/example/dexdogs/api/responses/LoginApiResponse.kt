package com.example.dexdogs.api.responses

import com.squareup.moshi.Json

class LoginApiResponse(val message: String,
                       @Json(name = "is_success") val is_success: Boolean,
                       val data: UserResponse)