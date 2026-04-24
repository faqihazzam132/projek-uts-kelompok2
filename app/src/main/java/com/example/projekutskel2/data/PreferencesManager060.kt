package com.example.projekutskel2.data

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

class PreferencesManager060(context: Context) {
    private val sharedPreferences_060: SharedPreferences =
        context.getSharedPreferences("session_pref_060", Context.MODE_PRIVATE)

    fun saveUserSession_060(userId: Int, username: String) {
        val editor_060 = sharedPreferences_060.edit()
        editor_060.putInt("user_id_060", userId)
        editor_060.putString("username_060", username)
        editor_060.apply()
    }

    fun getUserId_060(): Int {
        return sharedPreferences_060.getInt("user_id_060", -1)
    }

    fun getUsername_060(): String? {
        return sharedPreferences_060.getString("username_060", null)
    }

    fun clearSession_060() {
        val editor_060 = sharedPreferences_060.edit()
        editor_060.clear()
        editor_060.apply()
    }
}
