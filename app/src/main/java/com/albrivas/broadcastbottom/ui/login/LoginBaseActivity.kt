package com.albrivas.broadcastbottom.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.databinding.LoginBaseActivityBinding
import kotlinx.android.synthetic.main.activity_login_base.*
import java.util.*
import kotlin.concurrent.schedule

class LoginBaseActivity : AppCompatActivity() {

    private lateinit var binding: LoginBaseActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.ThemeSplash)
        super.onCreate(savedInstanceState)
        binding = LoginBaseActivityBinding.inflate(layoutInflater, fragment_container_login, false)
        setContentView(binding.root)
    }

    override fun onBackPressed() {

    }
}