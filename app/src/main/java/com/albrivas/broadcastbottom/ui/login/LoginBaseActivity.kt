package com.albrivas.broadcastbottom.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.databinding.ActivityLoginBaseBinding
import kotlinx.android.synthetic.main.activity_login_base.*

class LoginBaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.ThemeSplash)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBaseBinding.inflate(layoutInflater, fragment_container_login, false)
        setContentView(binding.root)
    }

    override fun onBackPressed() {

    }
}