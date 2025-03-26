package com.example.dexdogs.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class SingUpDTO(
    val email:String,
    val password:String,
    @field:Json(name = "password_confirmation") val password_confirmation:String)