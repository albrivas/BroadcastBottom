package com.albrivas.broadcastbottom.injection

import android.app.Application
import com.albrivas.broadcastbottom.ui.home.HomeFragment
import com.albrivas.broadcastbottom.ui.home.HomeViewModel
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
    scope(named<HomeFragment>()) {
        viewModel { HomeViewModel() }
    }
}