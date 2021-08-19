package com.reusablescope.auth.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

import androidx.activity.result.ActivityResultCallback

import androidx.activity.result.contract.ActivityResultContracts

import android.content.Intent

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnCompleteListener

import android.R
import android.R.attr
import androidx.activity.result.ActivityResult
import com.google.android.gms.tasks.Task

import android.R.attr.data
import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.reusablescope.auth.utils.Constants
import com.facebook.AccessToken

import com.facebook.FacebookAuthorizationException

import com.facebook.FacebookException

import com.facebook.GraphResponse

import org.json.JSONObject

import com.facebook.GraphRequest
import com.facebook.GraphRequest.GraphJSONObjectCallback


class FacebookSignInActivity : AppCompatActivity(){

    private val TAG: String = FacebookSignInActivity::class.java.simpleName
    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        faceBookCallBackManager()
    }

    /**
     * facebook callback manager
     */
    private fun faceBookCallBackManager() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    getFbDetails(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.i(TAG, "onCancel: ")
                }

                override fun onError(exception: FacebookException) {
                    Log.i(TAG, "onError: "+ exception.message)
                    if (exception is FacebookAuthorizationException) {
                        if (AccessToken.getCurrentAccessToken() != null) {
                            LoginManager.getInstance().logOut()
                        }
                    }
                }
            })
    }

    /**
     * from accessToken get details of the fb user
     */
    private fun getFbDetails(accessToken: AccessToken?) {
        val request = GraphRequest.newMeRequest(
            accessToken
        ) { `object`, response ->
            LoginManager.getInstance().logOut()
            if (`object` != null) {
                Log.i(TAG, "onCompleted: " + "availble")
                val firstName = `object`.optString("first_name")
                val lastName = `object`.optString("last_name")
                var email = ""
                if (`object`.optString("email") != null) {
                    email = `object`.optString("email")
                }
                val socialId = `object`.optString("id")
                val intent = Intent()
                intent.putExtra(Constants.KEY.LAST_NAME, lastName)
                intent.putExtra(Constants.KEY.FIRST_NAME, firstName)
                intent.putExtra(Constants.KEY.SOCIAL_ID, socialId)
                intent.putExtra(Constants.KEY.EMAIL, email)
                intent.putExtra(Constants.KEY.SOCIAL_TYPE, Constants.VALUE.FACEBOOK)
                setResult(Constants.VALUE.SOCIAL_SIGN_IN, intent)
            } else {
                Log.i(TAG, "onCompleted: error" + response.error.errorCode)
                setResult(Activity.RESULT_CANCELED)
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "id,name,first_name, last_name, email")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
    }

}