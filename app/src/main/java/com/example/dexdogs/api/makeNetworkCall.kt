package com.example.dexdogs.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> {
    return withContext(Dispatchers.IO) {
        try {
            ApiResponseStatus.Success(call())
        } catch (e: UnknownHostException) {
            ApiResponseStatus.Error("No hay conexión a internet")
        } catch (e: Exception) {
            val errorMessage= when (e.message) {
                "sign_up_error" -> "Error al crear la cuenta"
                "sign_in_error" -> "Error al iniciar sesión"
                "user_already_exists" -> "El usuario ya existe"
                else -> "Error desconocido"
            }
            ApiResponseStatus.Error(errorMessage)
        }
    }
}