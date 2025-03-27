package com.example.dexdogs.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


object AuthenticationInterceptor  : Interceptor {

    private var sessionToken: String? = null

    fun setSessionToken(sessionToken:String){
        this.sessionToken=sessionToken
    }

    override  fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        if (request.headers["needs_authentication"] != null) {
            if (sessionToken == null) {
                Log.e("ApiServiceInterceptor", "Session token is null, unable to add auth-token to request")
            } else {
                requestBuilder.addHeader("auth-token", sessionToken!!)
            }
        }

        return chain.proceed(requestBuilder.build())
    }
}
