package com.example.dexdogs.data.model

import com.squareup.moshi.Json

class SingUpDTO(
    val email:String,
    val password:String,
    @field:Json(name = "password_confirmation") val password_confirmation:String)