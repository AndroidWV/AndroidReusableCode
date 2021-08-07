package com.reusablescope.auth.utils

import android.content.Context

import android.content.SharedPreferences
import java.lang.RuntimeException


class SharedPref(context: Context) {
    val prefsFile: String = context.getPackageName()
    private var instance: SharedPref? = this
    private var sharedPreferences: SharedPreferences? = context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor? = sharedPreferences?.edit()


    fun getPrefsHelper(): SharedPref? {
        return instance
    }

    public fun init(context: Context) {
        if (instance == null) {
            SharedPref(context)
        }
    }

    public fun delete(key: String?) {
        if (sharedPreferences!!.contains(key)) {
            editor!!.remove(key).commit()
        }
    }

    public fun savePref(key: String?, value: Any?) {
        delete(key)
        if (value is Boolean) {
            editor!!.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Int) {
            editor!!.putInt(key, (value as Int?)!!)
        } else if (value is Float) {
            editor!!.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            editor!!.putLong(key, (value as Long?)!!)
        } else if (value is String) {
            editor!!.putString(key, value as String?)
        } else if (value is Enum<*>) {
            editor!!.putString(key, value.toString())
        } else if (value != null) {
            throw RuntimeException("Attempting to save non-primitive preference")
        }
        editor!!.commit()
    }

    fun <T> getPref(key: String?): T? {
        return sharedPreferences!!.all[key] as T?
    }

    fun <T> getPref(key: String?, defValue: T): T {
        val returnValue = sharedPreferences!!.all[key] as T?
        return returnValue ?: defValue
    }

    fun isPrefExists(key: String?): Boolean {
        return sharedPreferences!!.contains(key)
    }
}