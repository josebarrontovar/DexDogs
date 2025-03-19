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
            ApiResponseStatus.Error(e.message ?: "Error desconocido")
        }
    }
}