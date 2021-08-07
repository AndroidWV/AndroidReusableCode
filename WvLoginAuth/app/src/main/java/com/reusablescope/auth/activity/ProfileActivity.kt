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
import com.reusablescope.auth.databinding.ActivityProfileBinding
import java.lang.IllegalStateException


 class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        initAction()
    }

    private fun initAction() {
        binding.listener = this // set listener for click event
        /*inti text watcher*/


    }

    override fun onClick(view: View?) {


    }

}