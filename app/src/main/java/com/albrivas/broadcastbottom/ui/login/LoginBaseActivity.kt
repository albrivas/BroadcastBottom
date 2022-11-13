/*
 * File: LoginBaseActivity.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.databinding.ActivityLoginBaseBinding

class LoginBaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.ThemeSplash)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {

    }
}