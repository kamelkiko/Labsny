package com.example.labsny.util

import android.content.Context
import android.content.SharedPreferences

object PrefsUtil {
    private var sharedPreferences: SharedPreferences? = null
    fun initPrefsUtil(context: Context): PrefsUtil {
        sharedPreferences =
            context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return this
    }

    var day: String?
        get() = sharedPreferences?.getString(KEY_DAY, "")
        set(value) {
            sharedPreferences?.edit()?.putString(KEY_DAY, value)?.apply()
        }


    private const val KEY_DAY = "day"
}