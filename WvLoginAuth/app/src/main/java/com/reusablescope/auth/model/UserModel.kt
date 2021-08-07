package com.reusablescope.auth.model

data class UserModel(var firstName: String,
                     var lastName: String,
                     var gender: Int,
                     var email: String,
                     var mobile: String,
                     var profile: String
                    )