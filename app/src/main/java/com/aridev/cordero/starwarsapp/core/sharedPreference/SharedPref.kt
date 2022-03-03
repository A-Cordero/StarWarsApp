package com.aridev.cordero.starwarsapp.core.sharedPreference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPref @Inject constructor(@ApplicationContext val context: Context){

    companion object {
        const val StyleApp = "styleApp"
        const val ThemeApp = "themeApp"
    }

    private val mSharedPref: SharedPreferences = context.getSharedPreferences(context.packageName,
        Activity.MODE_PRIVATE)

    fun read(key: String, defValue: String): String {
        return mSharedPref.getString(key, defValue)!!
    }

    fun write(key: String?, value: String?) {
        val prefsEditor: SharedPreferences.Editor = mSharedPref.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    fun read(key: String?, defValue: Boolean): Boolean {
        return mSharedPref.getBoolean(key, defValue)
    }

    fun write(key: String?, value: Boolean) {
        val prefsEditor: SharedPreferences.Editor = mSharedPref.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }

    fun read(key: String?, defValue: Int): Int {
        return mSharedPref.getInt(key, defValue)
    }

    fun write(key: String?, value: Int?) {
        val prefsEditor: SharedPreferences.Editor = mSharedPref.edit()
        prefsEditor.putInt(key, value!!).apply()
    }
}