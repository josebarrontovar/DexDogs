package com.example.dexdogs.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


class LoginDTO(
    val email:String,
    val password:String)