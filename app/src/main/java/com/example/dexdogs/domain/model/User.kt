package com.example.dexdogs.domain.model

import android.app.Activity

class User(
    val id: Long,
    val email: String,
    val authenticationToken: String
) {
    companion object {
        fun setLoggedUser(activity: Activity, userData: User) {
            activity.getSharedPreferences("auth", Activity.MODE_PRIVATE).edit()
                .putLong("id", userData.id)
                .putString("email", userData.email)
                .putString("authenticationToken", userData.authenticationToken)
                .apply()
        }

        fun getLoggedUser(activity: Activity): User? {
            val pref = activity.getSharedPreferences("auth", Activity.MODE_PRIVATE)
            val userId = pref.getLong("id", 0)

            if (userId == 0L) return null

            return User(
                userId,
                pref.getString("email", "") ?: "",
                pref.getString("authenticationToken", "") ?: ""
            )
        }

        fun removeSharedPreference(activity: Activity){
            activity.getSharedPreferences("auth", Activity.MODE_PRIVATE).edit().clear().apply()

        }
    }
}
