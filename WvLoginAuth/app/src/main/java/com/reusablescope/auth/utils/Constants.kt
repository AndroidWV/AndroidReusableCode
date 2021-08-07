package com.reusablescope.auth.utils

class Constants {
    interface KEY {
        companion object{
            const val IS_USER_LOGGED_IN: String = "userLoggedIn"
            const val USER_MODEL: String = "user_model"
        }
    }

    interface VALUE {
        companion object {
            const val LEFT = 1
            const val RIGHT = 2
        }
    }
}