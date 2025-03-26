package com.example.dexdogs.data.model.responses

import com.squareup.moshi.Json

class SignUpApiResponse( val message: String,
                         @Json(name = "is_success") val is_success: Boolean,
                         val data: UserResponse
)