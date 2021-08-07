package com.reusablescope.auth.activity

import android.content.Context
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
import java.lang.IllegalStateException


class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        initAction()
    }

    private fun initAction() {
        binding.listener = this // set listener for click event
        /*gender adapter initialize*/
        val genders = resources.getStringArray(R.array.genders)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genders)
        binding.etGender.setAdapter(arrayAdapter)

        /*inti text watcher*/

        binding.etFirstName.addTextChangedListener(editTextWatcher(this, binding.etFirstName, binding))
        binding.etLastName.addTextChangedListener(editTextWatcher(this, binding.etLastName, binding))
        binding.etGender.addTextChangedListener(editTextWatcher(this, binding.etGender, binding))
        binding.etEmail.addTextChangedListener(editTextWatcher(this, binding.etEmail, binding))
        binding.etMobile.addTextChangedListener(editTextWatcher(this, binding.etMobile, binding))
        binding.etPassword.addTextChangedListener(editTextWatcher(this, binding.etPassword, binding))
        binding.etConfirmPassword.addTextChangedListener(editTextWatcher(this, binding.etConfirmPassword, binding))
    }

    override fun onClick(view: View?) {
            if ( view == binding.btnSignUp && WValidationLib.isEmpty(binding.tiFirstName, binding.etFirstName, getString(R.string.first_name_required), true) &&
                    WValidationLib.isEmpty(binding.tiLastName, binding.etLastName, getString(R.string.last_name_required), true) &&
                    WValidationLib.isEmpty(binding.tiGender, binding.etGender, getString(R.string.please_choose_gender), true) &&
                    WValidationLib.isEmailAddress(binding.tiEmail, binding.etEmail,getString(R.string.email_is_required),getString(R.string.enter_valid_email), true ) &&
                    WValidationLib.isValidPhone(binding.tiMobile, binding.etMobile, getString(R.string.mobile_number_is_required), getString(R.string.valid_mobile_number),true) &&
                    WValidationLib.isPassword(binding.tiPassword, binding.etPassword, getString(R.string.password_is_required),getString(
                                            R.string.password_minimum_length), true) &&
                        WValidationLib.isConfirmPasswordValidation(binding.tiPassword, binding.etPassword, binding.tiConfirmPassword, binding.etConfirmPassword,
                                getString(R.string.confirm_password_required),getString(
                            R.string.password_minimum_length),
                            getString(R.string.password_is_required),getString(R.string.confirm_password_not_matched_with), true)
            ){
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }

            if (view == binding.btnLogin){
                onBackPressed()
            }

    }

    /*edittext validation watcher class*/
    class editTextWatcher constructor(private val context: Context ,private val view: View, private val binding: ActivitySignUpBinding) : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            Log.i("TAG", "afterTextChanged: $editable")
            when (view.id) {
                R.id.etFirstName -> WValidationLib.isEmpty(binding.tiFirstName, binding.etFirstName, context.getString(R.string.first_name_required), true)

                R.id.etLastName -> WValidationLib.isEmpty(binding.tiLastName, binding.etLastName, context.getString(R.string.last_name_required), true)

                R.id.etGender -> WValidationLib.isEmpty(binding.tiGender, binding.etGender, context.getString(R.string.please_choose_gender), true)

                R.id.etEmail -> WValidationLib.isEmailAddress(binding.tiEmail, binding.etEmail,context.getString(R.string.email_is_required),context.getString(R.string.enter_valid_email), true )

                R.id.etMobile -> WValidationLib.isValidPhone(binding.tiMobile, binding.etMobile, context.getString(R.string.mobile_number_is_required), context.getString(R.string.valid_mobile_number),true)

                R.id.etPassword -> WValidationLib.isPassword(binding.tiPassword, binding.etPassword, context.getString(R.string.password_is_required),context.getString(
                    R.string.password_minimum_length), true)

                R.id.etConfirmPassword -> WValidationLib.isConfirmPasswordValidation(binding.tiPassword, binding.etPassword, binding.tiConfirmPassword, binding.etConfirmPassword,
                    context.getString(R.string.confirm_password_required),context.getString(
                        R.string.password_minimum_length),
                    context.getString(R.string.password_is_required),context.getString(R.string.confirm_password_not_matched_with), true)
                else -> throw IllegalStateException("Unexpected value: $view")
            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    }
}