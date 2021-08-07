package com.reusablescope.auth.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.reusablescope.auth.R
import com.reusablescope.auth.databinding.ActivitySignUpBinding
import com.reusablescope.auth.utils.WValidationLib

import android.text.Editable

import android.text.TextWatcher
import android.util.Log
import com.reusablescope.auth.databinding.ActivityLoginBinding
import java.lang.IllegalStateException


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initAction()
    }

    private fun initAction() {
        binding.listener = this // set listener for click event
        /*inti text watcher*/

        binding.etEmail.addTextChangedListener(editTextWatcher(this, binding.etEmail, binding))
        binding.etMobile.addTextChangedListener(editTextWatcher(this, binding.etMobile, binding))
        binding.etPassword.addTextChangedListener(editTextWatcher(this, binding.etPassword, binding))

    }

    override fun onClick(view: View?) {
            if ( view == binding.btnLogin &&
                    WValidationLib.isEmailAddress(binding.tiEmail, binding.etEmail,getString(R.string.email_is_required),getString(R.string.enter_valid_email), true ) &&
                    WValidationLib.isPassword(binding.tiPassword, binding.etPassword, getString(R.string.password_is_required),getString(
                                            R.string.password_minimum_length), true)
            ){
                val  intent = Intent(this,ProfileActivity::class.java)
                startActivity(intent)
            } else if (view == binding.btnSignUp){
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }

    }

    /*edittext validation watcher class*/
    class editTextWatcher constructor(private val context: Context ,private val view: View, private val binding: ActivityLoginBinding) : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            Log.i("TAG", "afterTextChanged: $editable")
            when (view.id) {

                R.id.etEmail -> WValidationLib.isEmailAddress(binding.tiEmail, binding.etEmail,context.getString(R.string.email_is_required),context.getString(R.string.enter_valid_email), true )

                R.id.etMobile -> WValidationLib.isValidPhone(binding.tiMobile, binding.etMobile, context.getString(R.string.mobile_number_is_required), context.getString(R.string.valid_mobile_number),true)

                R.id.etPassword -> WValidationLib.isPassword(binding.tiPassword, binding.etPassword, context.getString(R.string.password_is_required),context.getString(
                    R.string.password_minimum_length), true)

                else -> throw IllegalStateException("Unexpected value: $view")
            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    }
}