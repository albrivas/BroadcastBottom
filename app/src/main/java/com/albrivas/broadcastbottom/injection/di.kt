package com.albrivas.broadcastbottom.injection

import android.app.Application
import com.albrivas.broadcastbottom.ui.base.MainActivity
import com.albrivas.broadcastbottom.ui.base.MainViewModel
import com.albrivas.broadcastbottom.ui.home.HomeFragment
import com.albrivas.broadcastbottom.ui.home.HomeViewModel
import com.albrivas.broadcastbottom.ui.login.LoginFragment
import com.albrivas.broadcastbottom.ui.login.LoginViewModel
import com.albrivas.broadcastbottom.ui.login.SignUpFragment
import com.albrivas.broadcastbottom.ui.notifications.NotificationFragment
import com.albrivas.broadcastbottom.ui.notifications.NotificationViewModel
import com.albrivas.broadcastbottom.ui.settings.SettingsFragment
import com.albrivas.broadcastbottom.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDI)
        modules(listOf(scopeModule))
    }
}

private val scopeModule = module {
    scope(named<LoginFragment>()) {
        viewModel { LoginViewModel() }
    }

    scope(named<SignUpFragment>()) {
        viewModel { LoginViewModel() }
    }

    scope(named<HomeFragment>()) {
        viewModel { HomeViewModel() }
    }

    scope(named<MainActivity>()) {
        viewModel { MainViewModel() }
    }

    scope(named<NotificationFragment>()) {
        viewModel { NotificationViewModel() }
    }

    scope(named<SettingsFragment>()) {
        viewModel { SettingsViewModel() }
    }
}