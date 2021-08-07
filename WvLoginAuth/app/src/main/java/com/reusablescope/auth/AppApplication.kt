package com.reusablescope.auth

import android.app.Application
import android.content.Context
import com.reusablescope.auth.utils.Constants
import com.reusablescope.auth.utils.SharedPref


class AppApplication : Application() {
    private lateinit var context :Context
    private lateinit var sharedPref: SharedPref

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        sharedPref = SharedPref(context)
    }

    private fun setUserLogin(boolean: Boolean){
        sharedPref.savePref(Constants.KEY.IS_USER_LOGGED_IN, boolean)
    }

    private fun setUser(userGson: String){
        sharedPref.savePref(Constants.KEY.USER_MODEL, userGson)
    }

    fun isUserLoggedIn(): Boolean? {
        return if (sharedPref.isPrefExists(Constants.KEY.IS_USER_LOGGED_IN)) {
            sharedPref.getPref<Boolean>(Constants.KEY.IS_USER_LOGGED_IN)
        } else {
            false
        }
    }
}