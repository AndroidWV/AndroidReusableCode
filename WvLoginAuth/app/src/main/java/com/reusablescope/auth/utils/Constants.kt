package com.reusablescope.auth.utils

class Constants {
    interface KEY {
        companion object{
            const val IS_USER_LOGGED_IN: String = "userLoggedIn"
            const val USER_MODEL: String = "user_model"
            const val FIRST_NAME: String = "first_name"
            const val LAST_NAME: String = "last_name"
            const val EMAIL: String = "email"
            const val SOCIAL_ID: String = "social_id"
            const val SOCIAL_TYPE ="social_type"
        }
    }

    interface VALUE {
        companion object {
            const val LEFT = 1
            const val RIGHT = 2
            const val SOCIAL_SIGN_IN = 99
            const val GOOGLE = 1
            const val FACEBOOK =2
        }
    }
}