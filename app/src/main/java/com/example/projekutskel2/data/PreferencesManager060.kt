package com.example.projekutskel2.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager139(context: Context) {
    private val sharedPreferences_139: SharedPreferences =
        context.getSharedPreferences("session_pref_139", Context.MODE_PRIVATE)

    fun saveUserSession_139(userId: Int, username: String) {
        val editor_139 = sharedPreferences_139.edit()
        editor_139.putInt("user_id_139", userId)
        editor_139.putString("username_139", username)
        editor_139.apply()
    }

    fun getUserId_139(): Int {
        return sharedPreferences_139.getInt("user_id_139", -1)
    }

    fun getUsername_139(): String? {
        return sharedPreferences_139.getString("username_139", null)
    }

    fun clearSession_139() {
        val editor_139 = sharedPreferences_139.edit()
        editor_139.clear()
        editor_139.apply()
    }
}
