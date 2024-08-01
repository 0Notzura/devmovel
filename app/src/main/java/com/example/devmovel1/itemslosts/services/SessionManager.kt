package com.example.devmovel1.itemslosts.services

import android.content.Context
import android.content.SharedPreferences
import com.example.devmovel1.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.str_app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveToken(token: String) {
        val edit = prefs.edit()
        edit.putString(USER_TOKEN, token)
        edit.apply()
    }

    fun fetchToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun removeToken() {
        val edit = prefs.edit()
        edit.remove(USER_TOKEN)
        edit.apply()
    }

}