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
import com.reusablescope.auth.utils.Constants


class GoogleSignInActivity : AppCompatActivity(){

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val TAG: String = GoogleSignInActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        val signInIntent = mGoogleSignInClient?.signInIntent
        activityResultLauncherGoogleSignIn.launch(signInIntent)
    }

    val activityResultLauncherGoogleSignIn = registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->


        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        handleSignInResult(task)

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        if (completedTask.isSuccessful()) {
            val acct: GoogleSignInAccount = completedTask.getResult()
            if (acct != null) {
               val socialId = acct.id
               val firstName = acct.givenName
               val lastName = acct.familyName
               val email = acct.email
               val intent = Intent()
                intent.putExtra(Constants.KEY.LAST_NAME, lastName)
                intent.putExtra(Constants.KEY.FIRST_NAME, firstName)
                intent.putExtra(Constants.KEY.SOCIAL_ID, socialId)
                intent.putExtra(Constants.KEY.EMAIL, email)
                intent.putExtra(Constants.KEY.SOCIAL_TYPE, Constants.VALUE.GOOGLE)
                setResult(Constants.VALUE.SOCIAL_SIGN_IN, intent)
            }else{
                setResult(Activity.RESULT_CANCELED)
            }
            googleSignOut()

        } else {
            Log.i(TAG, "handleSignInResult: Login cancel "+completedTask.isCanceled )
            Toast.makeText(this,"Login cancel", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    /*google sign out intent*/
    private fun googleSignOut() {
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener(this,
                { })
        finish()
    }
}