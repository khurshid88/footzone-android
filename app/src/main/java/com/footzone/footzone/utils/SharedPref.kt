package com.footzone.footzone.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPref @Inject constructor(@ApplicationContext val context: Context) {
    val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

    fun saveLogIn(key: String, data: Boolean) {
        sharedPref.edit().putBoolean(key, data).apply()
    }

    fun getLogIn(key: String, data: Boolean): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun saveUserId(key: String, data: String) {
        sharedPref.edit().putString(key, data).apply()
    }

    fun getUserID(key: String, data: String): String {
        return sharedPref.getString(key, data)!!
    }

    fun saveUserToken(key: String, data: String) {
        sharedPref.edit().putString(key, data).apply()
    }

    fun getUserToken(key: String, data: String): String {
        return sharedPref.getString(key, data)!!
    }

    fun saveIsOwner(key: String, data: Boolean) {
        sharedPref.edit().putBoolean(key, data).apply()
    }

    fun getIsOwner(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun saveLanguage(key: String, language: String) {
        sharedPref.edit().putString(key, language).apply()
    }

    fun getLanguage(key: String, language: String): String {
        return sharedPref.getString(key, language)!!
    }

    fun saveFirebaseToken(tokenKey: String, firebaseToken: String) {
        sharedPref.edit().putString(tokenKey, firebaseToken).apply()
    }
    fun getFirebaseToken(keyToken: String): String {
        return sharedPref.getString(keyToken, "")!!
    }
}